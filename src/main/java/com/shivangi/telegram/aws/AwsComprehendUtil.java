package com.shivangi.telegram.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectDominantLanguageRequest;
import com.amazonaws.services.comprehend.model.DetectDominantLanguageResult;
import com.amazonaws.services.comprehend.model.DominantLanguage;

public class AwsComprehendUtil {

	// Get ACCESS_KEY from AWS
	private static final String ACCESS_KEY = "";
	// Get SECRET_KEY from AWS
	private static final String SECRET_KEY = "";

	private AwsComprehendUtil() {
	}

	private static AmazonComprehend comprehendClient;

	private static synchronized void initialize() {
		if (comprehendClient == null) {
			AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
			comprehendClient = AmazonComprehendClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
			System.out.println("****AWS ComprehendClient initialized!!********");
		}
	}
	
	public static String getLanguageCode(String text) {
		initialize();
		DetectDominantLanguageRequest detectDominantLanguageRequest = new DetectDominantLanguageRequest()
				.withText(text);
		DetectDominantLanguageResult detectDominantLanguageResult = comprehendClient
				.detectDominantLanguage(detectDominantLanguageRequest);
		DominantLanguage language = detectDominantLanguageResult.getLanguages().get(0);
		System.out.println(language);
		if (text.length() > 15) {
			return language.getScore() > 0.75 ? language.getLanguageCode() : "en";
		}
		return language.getScore() > 0.99 ? language.getLanguageCode() : "en";
	}

	


}
