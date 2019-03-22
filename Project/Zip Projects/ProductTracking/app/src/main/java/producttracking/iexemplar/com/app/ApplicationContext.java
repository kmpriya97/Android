package producttracking.iexemplar.com.app;

import okhttp3.MediaType;

/*******************************************************************************
 * Created by iExemplar on 10/5/2016.
 * <p>
 * Copyright (c) 2016 iExemplar. All rights reserved.
 *******************************************************************************/

public interface ApplicationContext {

    String MQTT_SERVER_ENDPOINT = "tcp://52.36.175.99:1883";
    String MQTT_CONTROLLER_LIVEDATA_PUB_PREFIX = "airfi/organization/";
    String MQTT_CONTROLLER_LIVEDATA_PUB_SUFFIX = "/controllerlivestatus";

    String MQTT_CONTROLLER_DELETE_PUB_PREFIX = "airfi/controller/command/";
    String MQTT_CONTROLLER_DELETE_PUB_SUFFIX = "/status";

    MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    //Ids Key
    String USER_ID = "userId";
    String ORGANIZATION_ID = "organizationId";
    String DEPARTMENT_ID = "departmentId";
    String CONTROLLER_ID = "controllerId";
    String SYSTEM_ID = "systemId";


    String USER_NAME = "userName";
    String PASSWORD = "password";
    String KEY_VALUES = "keyValues";
    String KEY_VALUES_SMALL = "keyValues";
    String KEY = "key";
    String VALUES = "values";
    String RESULTS = "results";
    String STARTTIME = "startTime";
    String COMMENTS = "comments";
    String ENDTIME = "endTime";
    String USER = "user";
    String OAUTH_KEY = "oauthKey";
    String DESCRIPTION = "description";
    String TYPE = "type";
    String KEY_STATE = "state";
    String CHARACTERISTICS = "characteristics";
    String ERRORS = "errors";
    String TYPE_CATEGORY = "typeCategory";
    String TYPE_VALUE = "typeValue";

    String CAPS_TYPE = "TYPE";
    String SYSTEMS_TYPE = "SYSTEMS_TYPE";
    String SYSTEM = "SYSTEM";
    String SETTING = "SETTING";
    String SENSOR_NOTIFICATION = "SENSOR_NOTIFICATION";
    String NOTIFICATION_STATE = "NOTIFICATION_STATE";
    String KEY_DEVICE_MAC_ADDRESS = "DEVICE_MAC_ADDRESS";
    String KEY_EMAIL_ADDRESS = "EMAIL_ADDRESS";
    String KEY_FORGOT_PASSWORD = "FORGOT_PASSWORD";
    String KEY_DEVICE_TOKEN = "DEVICE_TOKEN";
    String PURCHASE_DATE = "PURCHASE_DATE";
    String WARRANTY_END_DATE = "WARRANTY_END_DATE";
    String CONTROLLER_SERIAL_NUMBER = "CONTROLLER_SERIAL_NUMBER";
    String KEY_CONTROLLER_ID = "CONTROLLER_ID";
    String CONTROLLER_CONFIG_STATUS = "CONTROLLER_CONFIG_STATUS";
    String NUMBER = "NUMBER";
    String CURRENT = "CURRENT";
    String MAKE = "MAKE";
    String MODEL = "MODEL";
    String ROLE_NAME = "roleName";
    String EDIT = "EDIT";
    String ADD = "ADD";
    String DELETE = "DELETE";
    String RESET = "RESET";


    String CODE = "code";
    String MESSAGE = "message";
    String SUCCESS = "SUCCESS";
    String ID = "id";
    String CREATED_AT = "createdAt";
    String NAME = "name";
    String ADDRESSES = "addresses";
    String DEPARTMENT = "description";
    String CLIENT_KEY = "ixmtexapiv0.1";
    String CLIENT_SECRET = "ixmtexapi2017";
    String STREETS = "streets";
    String STATES = "state";
    String CITY = "city";
    String COUNTRY = "country";
    String POSTAL_CODE = "postalCode";

    String EXCEPTION_JSON = "JsonException";
    String EXCEPTION = "Exception";
    String EXCEPTION_NUMBER_FORMAT = "Number format";
    String EXCEPTION_PARSE = "ParseException";
    String EXCEPTION_NULL_POINTER = "NullPointerException";
    String EXCEPTION_DIALOG_DISMISS = "Dialog Dismiss";
    String JSONEXCEPTION = "JSONException";
    String MQTT_EXCEPTION = "MQTT EXCEPTION";
    String MQTT_DELIVERY = "MQTT DELIVERED";
    String MQTT_DELETE_CALLED = "DELETECALLED";
    String MQTT_MSG = "MQTT MESSAGE";
    String MQTT_CONNECTION = "MQTT CONNECTION";
    String MQTT_TOPIC = "MQTT TOPIC";
    String MQTT_CONNECTION_SUCCESS = "Mqtt Server Connected";
    String MQTT_CONNECTION_FAILED = "Mqtt not Connected";
    String MQTT_CONNECTION_RECONNECT = "Mqtt Server reconnected";


    // global topic to receive app wide push notifications
    String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    String REGISTRATION_COMPLETE = "registrationComplete";
    String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    int NOTIFICATION_ID = 100;


    String UNIQUECODE = "uniqueCode";
    String PREFERENCES_ITEM = "preferenceItem";
    String PREFERENCE_ID = "preferenceId";

    String WEFT = "WEFT";
    String WARP = "WARP";
    String MINIMUM = "MINIMUM";
    String MAXIMUM = "MAXIMUM";
    String PICKS = "PICKS";
    String RANGE_FROM = "RANGE_FROM";
    String RANGE_TO = "RANGE_TO";
    String ALERT_TYPE = "ALERT_TYPE";
    String COLOR_NAME = "COLOR_NAME";
    String CAPS_DESCRIPTION = "DESCRIPTION";
    String COLOR_CODE = "COLOR_CODE";
    String DATE_TIME = "DATE_TIME";
    String CAPS_MESSAGE = "MESSAGE";
    String CAPS_NAME = "NAME";
    String VALUE_01 = "VALUE_01";
    String MODULE = "MODULE";
    String STATE = "STATE";
    String TIME_FROM = "TIME_FROM";
    String TIME_TO = "TIME_TO";

    String SHIFT_AVG = "SHIFT_AVG";
    String HOUR_AVG = "HOUR_AVG";
    String CONSTRUCTION = "CONSTRUCTION";
    String TOTAL_CFM_AVG = "TOTAL_CFM_AVG";


    String DURATION = "duration";
    String SUB_TYPE = "subType";
    String DESCRIPTION_VALUES = "DESCRIPTION";
    String BLOCKED = "BLOCKED";
    String ACTIVE = "ACTIVE";
    String CHANGE_PASSWORD = "CHANGE_PASSWORD";
    String NEW_PASSWORD = "NEW_PASSWORD";
    String OLD_PASSWORD = "OLD_PASSWORD";
    String USERNAME = "USER_NAME";
    String ADDRESS = "ADDRESS";
    String ORGANIZATION_NAME = "ORGANIZATION_NAME";
    String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    String CONTENT = "CONTENT";
    String START_DATE_TIME = "startDateTime";
    String END_DATE_TIME = "endDateTime";
    String CONTACT_PERSON = "CONTACT_PERSON";
    String CONTACT_EMAIL = "CONTACT_EMAIL";
    String CONTACT_NUMBER = "CONTACT_NUMBER";
    String TIME_ZONE_ID = "TIME_ZONE_ID";
    String DONE = "DONE";


    String STATIC_API_KEY_SENSOR_NOTIFICATION = "SENSOR_NOTIFICATION";
    String STATIC_LPM = "0";
    String STATIC_CFM = "0.00";
    int STATIC_YEAR = 1900;


    String FORMAT_DATE_YYYYMMDD = "yyyy-MM-dd";
    String FORMAT_TIME_AMPM = "hh:mm a";

    String INTENT_ERROR = "intentError";
    String INTENT_ERROR_TYPE = "intentErrorType";
    String INTENT_FLAG_TYPE = "flagType";
    String INTENT_TYPE = "type";
    String INTENT_STATE = "state";
    String INTENT_ALERT_TYPE = "alertType";
    String INTENT_ORGANIZATION_ID = "organizationId";
    String INTENT_DEPARTMENT_ID = "departmentId";
    String INTENT_CONTROLLER_ID = "controllerId";
    String INTENT_NOTIFICATION_ID = "notificationId";
    String INTENT_COLOR_CODE = "colorCode";
    String INTENT_DESCRIPTION = "description";
    String INTENT_MACHINE_ID = "machineId";
    String INTENT_DEPARTMENT_NAME = "departName";
    String INTENT_MACHINE_DETAILS = "machineDetails";
    String INTENT_MACHINE_NAME = "machineName";
    String INTENT_POSITION = "position";


}

