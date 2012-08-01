package waar.lib;

import java.io.*;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class ServerHandler {
	
	private static final String LOG_TAG = "Log : ";

	
	public static String postData(String url, ArrayList<NameValuePair> paramList) {
	    // On cr�� un client http
	    HttpClient httpclient = new DefaultHttpClient();
	    // On cr�� notre ent�te
	    HttpPost httppost = new HttpPost(url);
	    
	    String HTMLCodeResponse = "";

	    try {
	    	//Mode debug retir� ->
	    	paramList.add(new BasicNameValuePair("debug", "0"));
	    	
	    	HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, "UTF-8");
	    	
	        // Ajoute la liste � notre ent�te
	        httppost.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));

	        // On ex�cute la requ�te tout en r�cup�rant la r�ponse
	        HttpResponse response = httpclient.execute(httppost);
	        
	        /*
	        //On r�cup�re la r�ponse dans un InputStream
    		InputStream inputStream = response.getEntity().getContent();
 
    		//On cr�e un bufferedReader pour pouvoir stocker le r�sultat dans un string
    		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
 
    		
    		
    		
    		
    		//On lit ligne � ligne le bufferedReader pour le stocker dans le stringBuffer
    		String ligneCodeHTML = bufferedReader.readLine();
       		while (ligneCodeHTML != null){
    			HTMLCodeResponse += ligneCodeHTML;
    			ligneCodeHTML = bufferedReader.readLine();
    		}*/

	        HttpEntity entity = response.getEntity();
	        
	        HTMLCodeResponse = EntityUtils.toString(entity, HTTP.UTF_8);
	        
	        return HTMLCodeResponse;

	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
		return "";
	}
}


