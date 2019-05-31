package com.foodtruck.service.impl;

import com.foodtruck.bean.FoodTruckAddress;
import com.foodtruck.domainobjects.FoodTruckPermit;
import com.foodtruck.exception.LocationServiceException;
import com.foodtruck.repository.FoodTruckLocationRepository;
import com.foodtruck.repository.FoodTruckPermitRepository;
import com.foodtruck.service.GoogleLocationService;
import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.TravelMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GoogleLocationServiceImpl implements GoogleLocationService {

    private static final String SPACE = " ";
    private static final String COMMA = ",";

    @Autowired
    private FoodTruckLocationRepository foodTruckLocationRepository;

    @Autowired
    private FoodTruckPermitRepository foodTruckPermitRepository;

    private GeoApiContext distCalcer;

    @Override
    public FoodTruckPermit getNearestFoodTruck(List<FoodTruckAddress> destinationList)
            throws LocationServiceException, InterruptedException {
        int currentCount = 0;
        long resTimeTaken = 0;
        String resAddress = null;
        List<String> originAddressList = foodTruckLocationRepository.findAllFoodTrucks();
        DistanceMatrix distanceMatrix = getDistanceMatrix(destinationList, originAddressList);
        DistanceMatrixRow[] distanceMatrixRows = distanceMatrix.rows;
        for (DistanceMatrixRow distanceMatrixRow : distanceMatrixRows) {
            String originAddress = originAddressList.get(currentCount++);
            long timeTaken = distanceMatrixRow.elements[0].duration.inSeconds;
            if (timeTaken < resTimeTaken) {
                resAddress = originAddress;
                resTimeTaken = timeTaken;
            }
        }
        return foodTruckPermitRepository.findFoodTrucksByAddress(resAddress);
    }

    public DistanceMatrix getDistanceMatrix(List<FoodTruckAddress> destinationList, List<String> originAddressList) throws LocationServiceException, InterruptedException {

        List<String> resDestinationList = new ArrayList<>();
        for (FoodTruckAddress address : destinationList) {
            String destinationAddress = addressInString(address);
            resDestinationList.add(destinationAddress);
        }
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(distCalcer);
        return getDistanceMatrixFromGoogle(originAddressList, resDestinationList, req);

    }

    private DistanceMatrix getDistanceMatrixFromGoogle(List<String> originAddresses, List<String> destinationAddresses, DistanceMatrixApiRequest req)
            throws LocationServiceException, InterruptedException {
        DistanceMatrix result;
        try {
            log.info("Calling Google's location service for origins {} ,destinations :{} ",
                    originAddresses, destinationAddresses);
            result = req.origins(originAddresses.toArray(new String[originAddresses.size()]))
                    .destinations(destinationAddresses.toArray(new String[destinationAddresses.size()]))
                    .mode(TravelMode.DRIVING)
                    .avoid(DirectionsApi.RouteRestriction.TOLLS)
                    .language("en-US")
                    .await();
        } catch (ApiException | IOException e) {
            log.error("Error while calling google API :{}", e);
            throw new LocationServiceException("Error while calling google location api " + e);
        } catch (InterruptedException e) {
            log.error("Interrupted Exception while calling google Api {}", e);
            throw new InterruptedException("Error while calling google location api " + e);
        }
        return result;
    }

    private static String addressInString(FoodTruckAddress addressDTO) {
        return new StringBuilder().append(addressDTO.getAddressLine().replace(COMMA, SPACE))
                .append(SPACE)
                .append(addressDTO.getRegion().replace(COMMA, SPACE))
                .append(SPACE)
                .append(addressDTO.getCity().replace(COMMA, SPACE))
                .append(SPACE)
                .append(addressDTO.getState().replace(COMMA, SPACE))
                .append(SPACE)
                .append(addressDTO.getPincode().replace(COMMA, SPACE))
                .toString();
    }
}
