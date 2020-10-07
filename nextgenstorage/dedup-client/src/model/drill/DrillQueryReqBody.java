package model.drill;


import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DrillQueryReqBody {
	private String query;
	private String queryType = "SQL";

	public DrillQueryReqBody(String query) {
		this.query = query;
	}

	public HttpEntity getHttpEntity() {
		return new StringEntity(toString(), ContentType.APPLICATION_JSON);
	}

	public String getQuery() {
		return query;
	}

	public String getQueryType() {
		return queryType;
	}

	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}

//	public static void main(String[] args) throws UnsupportedOperationException, IOException {
//		QueryReqBody queryReqBody = new QueryReqBody("SELECT * FROM DSMT_GOC LIMIT 10");
//		System.out.println(queryReqBody.getHttpEntity());
//		System.out.println(queryReqBody);
//	}
}


