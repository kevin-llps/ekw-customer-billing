package fr.kevin.llps.ekw.customer.billing.service;

import fr.kevin.llps.ekw.customer.billing.domain.CompanyPrice;
import fr.kevin.llps.ekw.customer.billing.mapper.CompanyPriceMapper;
import fr.kevin.llps.ekw.customer.billing.repository.CompanyPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyPriceService {

    private final CompanyPriceRepository companyPriceRepository;
    private final CompanyPriceMapper companyPriceMapper;

    public Map<String, CompanyPrice> getCompanyPriceByEnergyTypes() {
        return companyPriceRepository.findAll()
                .stream()
                .map(companyPriceMapper::map)
                .map(companyPrice -> Map.entry(companyPrice.energyType(), companyPrice))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
