package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A class that represents data services page. Has methods which check functionality of
 * data browser, schema editor and data services settings.
 */

public class DataServicesPage extends MotechPage {

    public static final By ENTITY_NAME_FIELD = By.name("inputEntityName");
    public static final By NEW_ENTITY_BUTTON = By.id("newEntityButton");
    public static final By SAVE_ENTITY_BUTTON = By.id("saveNewEntityButton");

    public static final By SCHEMA_EDITOR_TAB = By.id("mdsTab_schemaEditor");
    public static final By DATA_BROWSER_TAB = By.id("mdsTab_dataBrowser");
    public static final By BROWSE_INSTANCES_BUTTON = By.id("browseInstancesButton");
    public static final By ADD_NEW_INSTANCE_BUTTON = By.id("addNewInstanceButton");
    public static final By ENTITY_SPAN = By.id("select2-chosen-2");

    public static final String HOME_PATH = "/module/server/home#";

    public DataServicesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Method creates new entity in MDS schema editor.
     * @param entityName new entity name
     * @return method returns text that appears in schema editor entity input after creating new entity, should be the same as new entity name, should be checked in tests
     */
    public DataServicesPage createNewEntity(String entityName) throws InterruptedException {
        waitUntilBlockUiIsGone();
        clickWhenVisible(SCHEMA_EDITOR_TAB);
        clickWhenVisible(NEW_ENTITY_BUTTON);
        waitForElement(ENTITY_NAME_FIELD);
        setTextToFieldNoEnter(ENTITY_NAME_FIELD, entityName);
        clickWhenVisible(SAVE_ENTITY_BUTTON);
        waitForElement(BROWSE_INSTANCES_BUTTON);
        return this;
    }

    /**
     * Method that goes to data services page and enters entity table.
     * @param entityName name of entity table that we want to enter
     */
    public DataServicesPage goToEntityTable(String entityName) throws InterruptedException {
        clickWhenVisible(DATA_BROWSER_TAB);
        waitUntilBlockUiIsGone();
        clickWhenVisible(By.id(String.format("entity_%s", entityName)));
        waitForElement(ADD_NEW_INSTANCE_BUTTON);
        return this;
    }

    public String getChosenEntityName() {
        return getText(ENTITY_SPAN);
    }

    @Override
    public String expectedUrlPath() {
        return HOME_PATH;
    }
}
