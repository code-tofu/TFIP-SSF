package sg.edu.nus.iss.app.corpusAnalyzer.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.corpusAnalyzer.model.Corpus;
import sg.edu.nus.iss.app.corpusAnalyzer.service.CorpusTextService;

@Repository
public class CorpusRepo {

    @Autowired
    private RedisTemplate <String,Object> template;
    //this has to be static?? parametrized? 
    private static final String CORPUS_KEY = "corpuskey";

    public void writeToRepo(List<Corpus> corpusInput){
        for (var cp: corpusInput){
            template.opsForHash().put(CORPUS_KEY, (cp.getWord() + " " + cp.getNextWord()),String.valueOf(cp.getCount()));
            //can combine getword and getnextword into  a model method
            //corpus key can be anything. corpus key is the key to access the value which is the hashset a.k.a name of the hashset
        }

    }

}

// package sg.edu.nus.iss.app.corpusAnalyzer.repository;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Repository;

// import sg.edu.nus.iss.app.corpusAnalyzer.model.Corpus;

// @Repository
// public class CorpusRepository {

// TODO: Why is this a new instance?
//     @Autowired
//     private RedisTemplate template;

//     private static final String CORPUS_KEY = "corpus";

//     public void saveCorpusResult(List<Corpus> reqArr){
//         for(Corpus c : reqArr){
//             template.opsForHash()
//                     .put(CORPUS_KEY, c.getWordNextWord(), 
//                     String.valueOf(c.getCount()));
//         }
//     }
// }