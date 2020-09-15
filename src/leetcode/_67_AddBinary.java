package leetcode;

public class _67_AddBinary {
    public static void main(String[] args) {
        String result = addBinary("1001", "10101");
        System.out.println(result);
    }

    public static String addBinary(String a, String b) {
        int lena = a.length() - 1, lenb = b.length() - 1;
        int numa=0, numb = 0, carry = 0;
        StringBuffer result = new StringBuffer();
        while(lena >= 0 || lenb >= 0){
            numa = lena < 0? 0: (int)(a.charAt(lena)) - (int)('0');
            numb = lenb < 0? 0: Integer.parseInt(String.valueOf(b.charAt(lenb)));
            result.insert(0, String.valueOf((numb + numa + carry) % 2));
            carry = (numa + numb + carry) / 2;
            lena--;
            lenb--;
        }
        if(carry > 0){
            result.insert(0, "1");
        }

        return result.toString();
    }
}
