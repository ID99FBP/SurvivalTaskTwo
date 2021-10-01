package test.example.survivaltasktwo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import test.example.survivaltasktwo.response.Translate

object  LanguageRepositry {
     fun getlanguage(text:String) = flow<Status<Translate>>{
         emit(Status.Loading)
         emit(Client.makerequest(q =text,source="auto",target = "ar"))
     }.flowOn(Dispatchers.IO)

}