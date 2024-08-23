package fr.kevin.llps.ekw.customer.billing.service;

import fr.kevin.llps.ekw.customer.billing.domain.IndividualPrice;
import fr.kevin.llps.ekw.customer.billing.mapper.IndividualPriceMapper;
import fr.kevin.llps.ekw.customer.billing.repository.IndividualPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndividualPriceService {

    private final IndividualPriceRepository individualPriceRepository;
    private final IndividualPriceMapper individualPriceMapper;

    public Map<String, IndividualPrice> getIndividualPriceByEnergyTypes() {
        return individualPriceRepository.findAll()
                .stream()
                .map(individualPriceMapper::map)
                .map(individualPrice -> Map.entry(individualPrice.energyType(), individualPrice))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
