package com.epam.tc.hw2.data;

import lombok.experimental.UtilityClass;
import org.testng.annotations.DataProvider;

@UtilityClass
public class TestDataProviders {

    @DataProvider(name = "boardName")
    Object[][] boardName() {
        return new Object[][] {{"Red Board"}};
    }

    @DataProvider(name = "boardNameListName")
    Object[][] boardNameListName() {
        return new Object[][] {{"Green Board", "Green List"}};
    }

    @DataProvider(name = "boardNameListNameCardName")
    Object[][] boardNameListNameCardName() {
        return new Object[][] {{"Black Board", "Black List", "Black Card"}};
    }
}
