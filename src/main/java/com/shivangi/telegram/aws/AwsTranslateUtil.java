package com.shivangi.telegram.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

public class AwsTranslateUtil {

	// Get ACCESS_KEY from AWS
	private static final String ACCESS_KEY = "";
	// Get SECRET_KEY from AWS
	private static final String SECRET_KEY = "";

	private AwsTranslateUtil() {
	}

	private static AmazonTranslate translateClient;

	private static synchronized void initialize() {
		if (translateClient == null) {
			AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
			translateClient = AmazonTranslateClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
			System.out.println("****AWS TranslateClient initialized!!********");
		}
	}

	// Here text is non-English text
		public static String translate(String text, String languageCode) {
			initialize();
			TranslateTextRequest request = new TranslateTextRequest().withText(text).withSourceLanguageCode(languageCode)
					.withTargetLanguageCode("en");
			TranslateTextResult result = translateClient.translateText(request);
			return result.getTranslatedText();
		}
	

}
