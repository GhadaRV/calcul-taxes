package com.ghada.calcul_taxes.service;

import com.ghada.calcul_taxes.model.Country;
import org.springframework.stereotype.Service;
import java.util.EnumMap;
import java.util.Map;

@Service
public class TaxService {

    private final Map<Country, TaxStrategy> strategies = new EnumMap<>(Country.class);

    public TaxService() {
        strategies.put(Country.FRANCE, new FranceTaxStrategy());
        strategies.put(Country.CANADA, new CanadaTaxStrategy());
        strategies.put(Country.US, new USTaxStrategy());
    }

    public TaxStrategy getStrategy(Country country) {
        return strategies.get(country);
    }
}
