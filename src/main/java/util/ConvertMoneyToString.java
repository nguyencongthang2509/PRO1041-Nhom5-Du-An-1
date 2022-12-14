package util;

import com.ibm.icu.text.RuleBasedNumberFormat;
import java.util.Locale;

/**
 *
 * @author thangncph26123
 */
public class ConvertMoneyToString {

    public static String convertMoneyToText(String input) {
        String output = "";
        try {
            RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(new Locale("vi", "VN"), RuleBasedNumberFormat.SPELLOUT);
            output = ruleBasedNumberFormat.format(Long.parseLong(input)) + " Đồng";
        } catch (Exception e) {
            output = "0 Đồng";
        }
        String array[] = output.split(" ");
        String outputConvert = "";
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].substring(0,1).toUpperCase() + array[i].substring(1);
            outputConvert += array[i] + " ";
        }
        return outputConvert;
    }
    
    public static void main(String[] args) {
        System.out.println(convertMoneyToText("100000"));
    }
}
