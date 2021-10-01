package test.example.survivaltasktwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import test.example.survivaltasktwo.databinding.ActivityMainBinding
import test.example.survivaltasktwo.response.Translate

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottom.setOnClickListener {
            val text=binding.editText.text.toString()
            getAnyThing(text)
        }
    }

    private fun getAnyThing(text:String) {
       lifecycleScope.launch {
           LanguageRepositry.getlanguage(text).collect { getStatus(it)
           }
       }
    }
    private fun getStatus(status:Status<Translate>){
        when (status){
            is Status.Error ->Toast.makeText(this,"Faild becauseo of ${status.message}",Toast.LENGTH_SHORT).show()
            Status.Loading -> Toast.makeText(this,"Translating...",Toast.LENGTH_SHORT).show()
            is Status.Success -> binding.textView.text=status.data.translatedText
        }

    }
    companion object{
        const val TAG="Hawraa"

    }

}