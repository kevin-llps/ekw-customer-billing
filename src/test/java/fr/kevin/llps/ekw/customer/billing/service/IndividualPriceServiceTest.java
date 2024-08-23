package fr.kevin.llps.ekw.customer.billing.service;

import fr.kevin.llps.ekw.customer.billing.domain.IndividualPrice;
import fr.kevin.llps.ekw.customer.billing.entities.IndividualPriceEntity;
import fr.kevin.llps.ekw.customer.billing.mapper.IndividualPriceMapper;
import fr.kevin.llps.ekw.customer.billing.repository.IndividualPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static fr.kevin.llps.ekw.customer.billing.sample.IndividualPriceEntitySample.individualPriceEntities;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualPriceSample.individualPrices;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndividualPriceServiceTest {

    @Mock
    private IndividualPriceRepository individualPriceRepository;

    @Mock
    private IndividualPriceMapper individualPriceMapper;

    @InjectMocks
    private IndividualPriceService individualPriceService;

    @Test
    void shouldGetIndividualPriceByEnergyTypes() {
        List<IndividualPriceEntity> individualPriceEntities = individualPriceEntities();
        List<IndividualPrice> individualPrices = individualPrices();

        when(individualPriceRepository.findAll()).thenReturn(individualPriceEntities);
        when(individualPriceMapper.map(individualPriceEntities.getFirst())).thenReturn(individualPrices.getFirst());
        when(individualPriceMapper.map(individualPriceEntities.getLast())).thenReturn(individualPrices.getLast());

        Map<String, IndividualPrice> individualPriceByEnergyTypes = individualPriceService.getIndividualPriceByEnergyTypes();

        assertThat(individualPriceByEnergyTypes).isNotNull()
                .hasSize(2)
                .contains(
                        entry(ELECTRICAL_ENERGY_TYPE, individualPrices.getFirst()),
                        entry(GAS_ENERGY_TYPE, individualPrices.getLast()));

        verify(individualPriceRepository).findAll();
        verify(individualPriceMapper, times(2)).map(any());
        verifyNoMoreInteractions(individualPriceRepository, individualPriceMapper);
    }

}
