package controller;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Transactions;
import service.TransactionsService;;

@CrossOrigin
@RestController
@RequestMapping("/transactionservice")
public class TransactionsController {

	@Autowired
	TransactionsService ts;

	@RequestMapping("/all")
	public ArrayList<Transactions> getAll() {
		return ts.getAll();
	}
	
	@RequestMapping(value = { "/types/{type}" }, method = RequestMethod.GET)
	public ArrayList<Transactions> getType(@PathVariable("type") String type) {
		return ts.getType(type);
	}
	
	@RequestMapping(value = { "/transaction/{id}" }, method = RequestMethod.GET)
	public Transactions getTrans(@PathVariable("id") String id) {
		return ts.getTrans(id);
	}
	
	@RequestMapping(value = { "/transaction/{id}" }, method = RequestMethod.PUT)
	public String save(@PathVariable("id") String id,@RequestBody String formData, ModelMap model) {
		try {
			JSONObject jsonObj = new JSONObject(formData);
			Transactions transactions = new Transactions();
			transactions.setId(Integer.parseInt(id));
			transactions.setAmount(Double.parseDouble(jsonObj.getString("amount")));
			transactions.setType(jsonObj.getString("type"));
			transactions.setParent_id(Long.parseLong(jsonObj.getString("parent_id")));
			return ts.save(transactions);
		}catch(Exception e) {
			return "{\"code\":100,\"description\":\"error when insert\"}";
		}
	}
	
	@RequestMapping(value = { "/sum/{id}" }, method = RequestMethod.GET)
	public String sum(@PathVariable("id") String id) {
		return ts.sum(id);
	}
	
	@RequestMapping(value = { "/count/{str}" }, method = RequestMethod.GET)
	public String count(@PathVariable("str") String str) {
		int max = 256;
		String word = "";
		int count[] = new int[max]; 
		  
        int len = str.length(); 
        for (int i = 0; i < len; i++) 
            count[str.charAt(i)]++; 
  
        char ch[] = new char[str.length()]; 
        for (int i = 0; i < len; i++) { 
            ch[i] = str.charAt(i); 
            int find = 0; 
            for (int j = 0; j <= i; j++) { 
                if (str.charAt(i) == ch[j])  
                    find++;                 
            } 
  
            if (find == 1)  {
            	word = word + str.charAt(i) + String.valueOf(count[str.charAt(i)]);
            }           
        } 
      	return word;
	}
}

