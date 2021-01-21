package leetcode;

public class _649_Dota2Senate {
    public static void main(String[] args) {
        String input = "RDRDD";
        System.out.println(predictPartyVictory(input));
    }
    public static String predictPartyVictory(String senate) {
        int R = 0, D = 0, removeR=0, removeD=0;
        char[] person = senate.toCharArray();

        for(char p: person){
            if(p=='R') {
                R++;
            }
            else {
                D++;
            }
        }

        while(R > 0 && D > 0){
            for(int i = 0; i< person.length; i++){
                char p = person[i];
                if(p == 'R'){
                    if(removeR > 0){
                        removeR--;
                        person[i]='#';
                    }else{
                        removeD++;
                        D--;
                    }
                }
                else if (p == 'D'){
                    if(removeD > 0){
                        person[i] = '#';
                        removeD--;
                    }else{
                        removeR++;
                        R--;
                    }
                }
            }
        }

        return R > 0?"Radiant":"Dire";
    }
}
