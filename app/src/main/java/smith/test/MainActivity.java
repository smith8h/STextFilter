package smith.test;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.itsaky.androidide.logsender.LogSender;
import smith.lib.tools.textfilter.STextFilter;
import smith.test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    
    ActivityMainBinding bind;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogSender.startLogging(this);
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        
//        String text = "This is my email: husseinshakir81@gmail.com";
//        STextFilter filter = new STextFilter(this);
//        if (filter.containsEmail(text)) {
//            // extract email from text
//            String email = text.substring(filter.start(), filter.end());
//        }
//        
//        if (filter.contains("my email", text)) {
//            // extract specific text from a whole text
//            String found = text.substring(filter.start(), filter.end());
//        }
        
    }
    
    public void check(View v) {
        STextFilter stf = new STextFilter(this);
        bind.tv.setText(stf.censorBannedWords(bind.et.getText().toString()));
    }
}
