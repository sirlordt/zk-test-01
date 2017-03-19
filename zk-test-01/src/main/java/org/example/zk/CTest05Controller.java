package org.example.zk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CTest05Controller extends SelectorComposer<Component> {

    private static final long serialVersionUID = 7144420432983379641L;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        
        super.doAfterCompose(comp);
        
    }    
    
    @Listen( "onClick=#buttonTest01" ) 
    public void buttonTest01onClick( Event event ) {
        
        createJSON( "/home/sirlordt/Documentos/data1.json" );
        parseJSON( "/home/sirlordt/Documentos/data.json" );
        
        /*
        final InputStream in = new FileInputStream("json.json");
        
        try {
        
            Iterator<JSONObject> it = new ObjectMapper().readValues( new JsonFactory().createJsonParser(in ), JSONObject.class );
            
            for ( Iterator it = new ObjectMapper().readValues(
                    new JsonFactory().createJsonParser(in), Map.class); it.hasNext();)
                System.out.println(it.next());
            
        }
        finally { 
            
            in.close();
            
        }*/ 
        
        
    }
    
    /*
     * This method create JSON String by using Jackson Streaming API.
     */
    public static void createJSON(String path) {
        
        try {
            
            JsonFactory jsonfactory = new JsonFactory();
            File jsonDoc = new File(path);
            OutputStream out = new FileOutputStream(jsonDoc);
            JsonGenerator generator = jsonfactory.createGenerator(out, JsonEncoding.UTF8);

            generator.writeStartObject();
            
            generator.writeStringField( "name", "Garrison" );
            generator.writeStringField( "gender", "MALE" );
            generator.writeStringField( "verified", "false" );
            generator.writeStringField( "userImage", "Rm9vYmFyIQ==" );
            
            /*
            generator.writeNumberField( "verified", 847332223 );

            generator.writeFieldName("address");
            generator.writeStartArray();
            generator.writeString("Unit - 232");
            generator.writeString("Sofia Streat");
            generator.writeString("Mumbai");
            generator.writeEndArray();
            */
            
            generator.writeEndObject();

            generator.writeStartObject();
            
            generator.writeStringField( "name", "Jane" );
            generator.writeStringField( "gender", "FEMALE" );
            generator.writeStringField( "verified", "false" );
            generator.writeStringField( "userImage", "DFREWEWE==" );
            
            generator.writeEndObject();
            
            generator.close();

            System.out.println("JSON file created successfully");

        } 
        catch ( Exception ex) {

            ex.printStackTrace();
            
        }
        
    }

    /*
     * This method parse JSON String by using Jackson Streaming API example.
     */
    public static void parseJSON( String strFileName ) {
        
        JsonParser jsonParser = null;
        
        try {
            
            ObjectMapper mapper = new ObjectMapper();
            File file = new File( strFileName );
            
            jsonParser = mapper.getFactory().createParser( file );
            
            JsonToken jsonToken = null;
            
            while ( ( jsonToken = jsonParser.nextToken() ) != null ) {
            
                if ( jsonToken.equals( JsonToken.START_OBJECT ) ) {
                    
                    JsonNode jsonNode = jsonParser.readValueAsTree();

                    System.out.println( "Readed object: " + jsonNode.toString() );
                    
                }
                
            }
            

        } 
        catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
        finally {
            
            if ( jsonParser != null ) {
                
                try {
                    
                    jsonParser.close();
                    
                } 
                catch ( Exception ex ) {

                    ex.printStackTrace();
                    
                }
                
            }
            
        }
        
    }


//Read more: http://javarevisited.blogspot.com/2015/03/parsing-large-json-files-using-jackson.html#ixzz4bnTBhLzh
    
    
}
