package sg.edu.nus.iss.app.workshop14.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.workshop14.model.Contact;

@Repository
public class AddressbookRepository {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    //autowired so no need to create new??
    //redisTemplate provides all the necessary opsForXXX methods to interact with redis database

    private static final String CONTACT_LIST = "contactlist";

    
    public void save(final Contact ctc){
        redisTemplate.opsForList()
                .leftPush(CONTACT_LIST, ctc.getId());
        redisTemplate.opsForHash()
            .put(CONTACT_LIST + "_Map", ctc.getId(), ctc);
        //put(String key, Object hashKey). key is contactlist_Map? what does the key do?
    }

    public Contact findById(final String contactId){
        //returns contact object matching contactID
        Contact result = (Contact) redisTemplate.opsForHash()
            .get(CONTACT_LIST + "_Map",
            contactId);
        //get(String key, Object hashKey). key is contactlist_Map? what does the key do?
            
        return result;
    }

    public List<Contact> findAll(int startIndex){
        //contract list is the key use to get from list?
        //range is the multi entry version of get.
        List<Object> fromContactList = redisTemplate.opsForList()
            .range(CONTACT_LIST, startIndex, 10);

        // ?????
        List<Contact> ctcs = redisTemplate.opsForHash()
            .multiGet(CONTACT_LIST + "_Map", fromContactList) //Get values for given hashKeys from hash at key?
            .stream()
            .filter(Contact.class::isInstance)
            .map(Contact.class::cast)
            .collect(Collectors.toList());

        return ctcs;
    }
}
