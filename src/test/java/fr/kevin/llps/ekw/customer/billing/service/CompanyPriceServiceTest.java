package fr.kevin.llps.ekw.customer.billing.service;

import fr.kevin.llps.ekw.customer.billing.domain.CompanyPrice;
import fr.kevin.llps.ekw.customer.billing.entities.CompanyPriceEntity;
import fr.kevin.llps.ekw.customer.billing.mapper.CompanyPriceMapper;
import fr.kevin.llps.ekw.customer.billing.repository.CompanyPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static fr.kevin.llps.ekw.customer.billing.sample.CompanyPriceEntitySample.companyPriceEntities;
import static fr.kevin.llps.ekw.customer.billing.sample.CompanyPriceSample.companyPrices;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyPriceServiceTest {

    @Mock
    private CompanyPriceRepository companyPriceRepository;

    @Mock
    private CompanyPriceMapper companyPriceMapper;

    @InjectMocks
    private CompanyPriceService companyPriceService;

    @Test
    void shouldGetCompanyPriceByEnergyTypes() {
        List<CompanyPriceEntity> companyPriceEntities = companyPriceEntities();
        List<CompanyPrice> companyPrices = companyPrices();

        when(companyPriceRepository.findAll()).thenReturn(companyPriceEntities);
        when(companyPriceMapper.map(companyPriceEntities.getFirst())).thenReturn(companyPrices.getFirst());
        when(companyPriceMapper.map(companyPriceEntities.getLast())).thenReturn(companyPrices.getLast());

        Map<String, CompanyPrice> companyPriceByEnergyTypes = companyPriceService.getCompanyPriceByEnergyTypes();

        assertThat(companyPriceByEnergyTypes).isNotNull()
                .hasSize(2)
                .contains(
                        entry(ELECTRICAL_ENERGY_TYPE, companyPrices.getFirst()),
                        entry(GAS_ENERGY_TYPE, companyPrices.getLast()));

        verify(companyPriceRepository).findAll();
        verify(companyPriceMapper, times(2)).map(any());
        verifyNoMoreInteractions(companyPriceRepository, companyPriceMapper);
    }

}
