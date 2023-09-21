import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speaker {

    private static VoiceManager voiceManager = VoiceManager.getInstance();
    private static Voice speaker;

    static {
        speaker = voiceManager.getVoice("kevin16");
        speaker.allocate();
    }

    private Speaker() {
    }

    public static void speak(String word) {
        speaker.speak(word);
    }

}
