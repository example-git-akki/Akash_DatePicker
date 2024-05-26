package Reusable;

public class Payload {

    public static String bodyData(String DynamicValue1, String DynamicValue2, String DynamicValue3) {


        String payload = "{\r\n" + " \"name\" : \"Rest Assured SBA Assesment\",\r\n" + " \"isbn\":  \""+DynamicValue1+"\",\r\n" + " \"aisle\":\""+DynamicValue2+"\",\r\n" + " \"author\":\""+DynamicValue3+"\"\r\n" + "}\r\n" + "";

        return payload;
    }

    public static String putData(String DynamicValue1, String DynamicValue2){
        String payload1=

                "{\n" + "  \"title\": \""+DynamicValue1+"\",\n" + "  \"body\": \""+DynamicValue2+"\",\n" + "  \"userId\": \"1\" \n}";
        return payload1;
    }
}
