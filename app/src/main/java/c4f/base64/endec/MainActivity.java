package c4f.base64.endec;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.EditText;
import android.graphics.drawable.GradientDrawable;
import android.widget.Button;
import android.graphics.Typeface;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.widget.Toast;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Base64;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import android.view.Window;
import android.annotation.SuppressLint;

public class MainActivity extends Activity 
{
	// ALL STRINGS
    final String primaryColor = new String("#22ff99");
	final String btnTextColor = new String("#000000");
	
	final String mainTitleText = new String("0xAREEB");
	final String subTitleText = new String("Base64 Encode/Decode");

	final String emptyInputText = new String("<font color=red>Error! &nbsp;</font><font color='#22ff99'>The Input must not be Empty..</font>");
	final String successMessage = new String("<font color='#22ff99'><b>Success!</b> Result copied To Clipboard..</font>");
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// Hide Navbar and Full Screen
		hideUI();
        
		setContentView(R.layout.activity_main);
		
		// Gradient Drawables
		GradientDrawable edTextDrawable = new GradientDrawable();
		GradientDrawable btnDrawable = new GradientDrawable();
		
		// TextViews
		TextView mT = findViewById(R.id.titleMain);
		TextView sT = findViewById(R.id.subtitle);
		TextView stvtext = findViewById(R.id.sourceTView);
		
		// Input Field
		final EditText stvedit = findViewById(R.id.sourceEText);
		
		// Buttons
		Button btnEncode = findViewById(R.id.convertBtn);
		Button btnDecode = findViewById(R.id.decodeBtn);
		
		
		// Drawable for Input Field (EditText)
		edTextDrawable.setStroke(3, Color.parseColor(primaryColor));
		edTextDrawable.setColor(Color.TRANSPARENT);
		float[] radiiii= {22,22,0,0,22,22,0,0};
		edTextDrawable.setCornerRadii(radiiii);
		
		// Drawable For Buttons
		btnDrawable.setStroke(3, Color.parseColor(primaryColor));
		btnDrawable.setColor(Color.parseColor(primaryColor));
		btnDrawable.setCornerRadii(radiiii);
		
		// Main Title Text
		mT.setText(mainTitleText);
		mT.setTextColor(Color.parseColor(primaryColor));
		
		// Sub Title Text
		sT.setText(subTitleText);
		sT.setTextColor(Color.parseColor(primaryColor));
		
		// Input Text Label
		stvtext.setText(new String("Please Enter the Data"));
		stvtext.setTextColor(Color.parseColor(primaryColor));
		
		// Input Field (EditText)
		stvedit.setTextColor(Color.parseColor(primaryColor));
		stvedit.setFocusedByDefault(false);
		stvedit.setBackground(edTextDrawable);
		stvedit.setText("");
		
		// Encode Button
		btnEncode.setBackground(btnDrawable);
		btnEncode.setTextColor(Color.parseColor(btnTextColor));
		btnEncode.setText(new String("Encode"));
		btnEncode.setTypeface(Typeface.MONOSPACE);
		btnEncode.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				final String textTarget = stvedit.getText().toString();
				
				if(textTarget.equals(""))
				{
				    Toast.makeText(getApplicationContext(), Html.fromHtml(emptyInputText), Toast.LENGTH_LONG).show();
				}
				else 
				{
					final String encodedString = new String(Base64.encode(textTarget.getBytes(StandardCharsets.UTF_8), 0));
					copyToClip(encodedString);
					Toast.makeText(getApplicationContext(), Html.fromHtml(successMessage), Toast.LENGTH_LONG).show();
					stvedit.setText("");
				}
			}
		});
		
		
		btnDecode.setBackground(btnDrawable);
		btnDecode.setTextColor(Color.parseColor(btnTextColor));
		btnDecode.setText(new String("Decode"));
		btnDecode.setTypeface(Typeface.MONOSPACE);
		btnDecode.setOnClickListener(new OnClickListener()
		{
				public void onClick(View v) 
				{
					final String textTarget = stvedit.getText().toString();
					
					if(textTarget.equals(""))
					{
						Toast.makeText(getApplicationContext(), Html.fromHtml(emptyInputText), Toast.LENGTH_LONG).show();
					}
					else 
					{
						final String decodedString = new String(Base64.decode(textTarget.getBytes(StandardCharsets.UTF_8), 0));
						copyToClip(decodedString);
						Toast.makeText(getApplicationContext(), Html.fromHtml(successMessage), Toast.LENGTH_LONG).show();
						stvedit.setText("");
					}
				}
			});
    }
	
	public final void copyToClip(String text){
		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); 
		ClipData clip = ClipData.newPlainText("", text);
		clipboard.setPrimaryClip(clip);
	}
	
	@SuppressLint("NewApi")
	public final void hideUI()
	{
		final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
			| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
			| View.SYSTEM_UI_FLAG_FULLSCREEN
			| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
			
		getWindow().getDecorView().setSystemUiVisibility(flags);
		
		final View decorView = getWindow().getDecorView();
		decorView
		    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
			{
				@Override
				public void onSystemUiVisibilityChange(int visibility)
				{
					if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN)==0)
					{
						decorView.setSystemUiVisibility(flags);
					}
				}
			});
	}
} 
