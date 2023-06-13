package utilities;

import java.io.File;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoAttributes;

public class ConvertVideoToMp4 {
	public static void convert(String oldPath, String newPath) {
		File source = new File(oldPath);
		File target = new File(newPath);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");// Audio encoding format
		audio.setBitRate(new Integer(800000));
		audio.setChannels(new Integer(1));
		audio.setSamplingRate(new Integer(22050));
		VideoAttributes video = new VideoAttributes();
		video.setCodec("libx264");// Video encoding format
		video.setBitRate(new Integer(2500000));// new
		video.setFrameRate(new Integer(15));// If the number setting is small, the video will freeze
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp4");
		attrs.setAudioAttributes(audio);
		attrs.setVideoAttributes(video);
		Encoder encoder = new Encoder();
		MultimediaObject multimediaObject = new MultimediaObject(source);
		try {
			encoder.encode(multimediaObject, target, attrs);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InputFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
