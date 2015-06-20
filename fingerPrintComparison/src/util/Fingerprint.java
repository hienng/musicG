package util;

import com.musicg.fingerprint.FingerprintManager;
import com.musicg.wave.Wave;

public class Fingerprint {
	public final byte[] fp;
	
	public final byte[] getFp() {
		return fp;
	}
	
	public Fingerprint(String s) {
		Wave wave = new Wave("waves/"+s);
		FingerprintManager fpManager = new FingerprintManager();

		fp = wave.getFingerprint();
		fpManager.saveFingerprintAsFile(fp,"waves/out/" + s + ".fingerprint");
	}
}
