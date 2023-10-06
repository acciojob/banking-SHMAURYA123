package com.driver;

import java.util.Arrays;
import java.util.HashMap;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
         super(name,balance,5000);
         this.tradeLicenseId=tradeLicenseId;
         if(balance<5000){
             throw new Exception("Insufficient Balance");
         }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
       if(!isValidLicense(tradeLicenseId)){

           if(!canRearrangeLicenseId(tradeLicenseId)){
             throw new Exception("Valid License can not be generated");
           }

            rearrangeId(tradeLicenseId);
       }
       else{
           this.tradeLicenseId=tradeLicenseId;
       }
    }

    public String rearrangeId(String s) {
        int[] freq= new int[26];
         char maxFreqChar='0';
         int maxFreq=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            freq[ch-'A']++;
            if(freq[ch-'A']>maxFreq){
                maxFreqChar=ch;
                maxFreq=freq[ch-'A'];
            }
        }
      // put the max freq char
        char[] res=new char[s.length()];
        int idx=0;
        while(freq[maxFreqChar-'A']-->0){
            res[idx]=maxFreqChar;
            idx+=2;
            }
        // iterate the remaining character
        for(int i=0;i<26;i++){
            int frequency=freq[i];
            while(frequency>0){
                if(idx>=s.length()) {
                    idx = 1;
                }
                    res[idx]=(char)(i+'A');
                    idx+=2;
                }
            }
        return Arrays.toString(res);
        }


    private boolean canRearrangeLicenseId(String tradeLicenseId) {
        int s=tradeLicenseId.length();
        HashMap<Character,Integer> map=new HashMap<>();
        char[] arr=tradeLicenseId.toCharArray();
        for(char ch:arr) {
                map.put(ch, map.getOrDefault(ch,0) + 1);
        }
        //find max freq char
        int max=0;
        for(int v:map.values()){
            if(v>max){
                max=v;
            }
        }
        boolean ans=false;
        if(max>(s+1)/2){
            ans=true;
        }
        return ans;
    }

    public boolean isValidLicense(String tradeLicenseId){
        int n=tradeLicenseId.length();

        for(int i=0;i<n-1;i++){
         if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1)){
             return false;
         }
        }
        return  true;
    }
}
