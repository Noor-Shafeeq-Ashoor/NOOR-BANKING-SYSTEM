package com.ga.banking.DataBase;

import com.ga.banking.models.Banker;

import java.util.ArrayList;
import java.util.List;

public class BankerData {

    private static final List<Banker> bankers =
            new ArrayList<>();

    static {
        bankers.add(new Banker(1, "ahmed", "ahmed@naz.bh", "FzfcNcc#8834"));
        bankers.add(new Banker(2, "sara", "sara@naz.bh", "N"));
        bankers.add(new Banker(3, "ali", "ali@naz.bh", "9999"));
    }

    public static List<Banker> getBankers() {
        return bankers;
    }
}