package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.TextField;
public class ControllerMinimization {
    @FXML
    private TextField n;

    @FXML
    private TextField vec;

    @FXML
    private Button btn;

    @FXML
    private Button btn2;

    @FXML
    private Label output;

    @FXML
    private Label output1;

    @FXML
    private Label output11;

    @FXML
    private Label output111;

    @FXML
    private Label nError;

    @FXML
    private Label vecError;

    final static List<String> listForOne = Stream.of("0", "1").collect(Collectors.toList());
    final static List<String> listForTwo = Stream.of("00", "01", "10", "11").collect(Collectors.toList());
    final static List<String> listForThree = Stream.of("000", "001", "010", "011", "100", "101", "110", "111").collect(Collectors.toList());
    final static List<String> listForFour = Stream.of("0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111").collect(Collectors.toList());
    final static List<String> listForFive = Stream.of("00000", "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010", "01011", "01100", "01101", "01110", "01111", "10000", "10001", "10010", "10011", "10100", "10101", "10110", "10111", "11000", "11001", "11010", "11011", "11100", "11101", "11110", "11111").collect(Collectors.toList());
    final static String CharacterOne = "a";
    final static String CharacterTwo = "ab";
    final static String CharacterThree = "abc";
    final static String CharacterFour = "abcd";
    final static String CharacterFive = "abcde";
    static int count = 0;
    static Set<String> setList = new LinkedHashSet<>();
    static Set<String> setList2 = new LinkedHashSet<>();
    static String text = "";
    static int countChar = 0;
    static Map<Integer, Character> map1 = new LinkedHashMap<>();
    static Map<Integer, Character> map2 = new LinkedHashMap<>();
    static List<String> arrayList = new ArrayList<>();
    static String result = "";

    @FXML
    void clickbtn2() {
        btn2.setOnAction(event -> cancel());
    }

    void cancel() {
        n.setText("");
        vec.setText("");
        output.setText("");
        output1.setText("");
        output11.setText("");
        output111.setText("");
        setList2 = new LinkedHashSet<>();
        setList = new LinkedHashSet<>();
        count = 0;
        result = "";
        text = "";
        countChar = 0;
        map1 = new LinkedHashMap<>();
        map2 = new LinkedHashMap<>();
        arrayList = new ArrayList<>();
    }

    @FXML
    void initialize() {


        btn.setOnAction(event ->
        {
            String stringN = n.getText();
            String stringVec = vec.getText();
            int a = Integer.parseInt(stringN);
            if (a < 1 || a > 5) {
                nError.setText("Ошибка, введите число от 1 до 5");

            }
            if (stringVec.length() != Math.pow(2, a)) {
                vecError.setText("Ошибка, некорректный вектор");
            } else {
                nError.setText("");
                vecError.setText("");
            }

            result = output(a, stringVec);

            if (a == 1) {
                QuineMethod(result);
                output.setText("Вывод: " + result);
            }
            if (a == 2) {
                QuineMethod(result);
                result = gluing(setList2);
                output.setText("Вывод: " + result);
            } else if (a == 3) {
                QuineMethod(result);
                result = gluing(setList);
                output1.setText("После склеивания: " + result);
                count = 0;
                QuineMethod(result);
                result = gluing(setList2);
                output.setText("Вывод: " + result);

            } else if (a == 4) {
                QuineMethod(result);
                result = gluing(setList);
                output11.setText("После склеивания: " + result);
                setList = new LinkedHashSet<>();
                count = 0;
                QuineMethod(result);
                result = gluing(setList);
                output1.setText("После склеивания: " + result);
                setList = new LinkedHashSet<>();
                count = 0;
                QuineMethod(result);
                result = gluing(setList2);
                output.setText("Вывод: " + result);
            } else if (a == 5) {
                QuineMethod(result);
                result = gluing(setList);
                output111.setText("После склеивания: " + result);
                setList = new LinkedHashSet<>();
                count = 0;
                QuineMethod(result);
                result = gluing(setList);
                output11.setText("После склеивания: " + result);
                setList = new LinkedHashSet<>();
                count = 0;
                QuineMethod(result);
                result = gluing(setList);
                output1.setText("После склеивания: " + result);
                setList = new LinkedHashSet<>();
                count = 0;
                QuineMethod(result);
                result = gluing(setList2);
                output.setText("Вывод: " + result);
            }
        });
    }

    public static String gluing(Set<String> set) {
        String result = "";
        for (String s : set) {
            result += s + " v ";
        }
        result = result.substring(0, result.length() - 3);
        return result;
    }

    // получаем СДНФ
    public static String output(int n, String vec) {
        char[] arr = vec.toCharArray();
        String result = "";

        for (int i = 0; i < arr.length; i++) {
            if (n == 1) {
                if (arr[i] == '1') {
                    result += (SDNF(listForOne.get(i), CharacterOne));
                }
            } else if (n == 2) {
                if (arr[i] == '1') {
                    result += (SDNF(listForTwo.get(i), CharacterTwo));
                }
            } else if (n == 3) {
                if (arr[i] == '1') {

                    result += (SDNF(listForThree.get(i), CharacterThree));
                }
            } else if (n == 4) {
                if (arr[i] == '1') {
                    result += (SDNF(listForFour.get(i), CharacterFour));
                }
            } else if (n == 5) {
                if (arr[i] == '1') {
                    result += (SDNF(listForFive.get(i), CharacterFive));
                }
            }
        }
        result = result.substring(0, result.length() - 3);
        return result;
    }

    // проверка после результрующего склеивания первой буквы
    public static List<String> validOneChar(List<String> list) {

        List<String> listGdeSovpadenia = new ArrayList<>();
        char[] mainChar = text.toCharArray();
        for (int i = 0; i < list.size(); i++) {

            String s = list.get(i);
            char[] chars = s.toCharArray();

            if (mainChar[0] == chars[0]) {
                countChar++;
                map1.put(countChar, mainChar[0]);
            } else {
                listGdeSovpadenia.add(s);
            }
        }
        int maxKey = 0;
        for (Map.Entry<Integer, Character> pair : map1.entrySet()) {
            int key = pair.getKey();

            if (maxKey < key) {
                maxKey = key;
            }
        }

        if (maxKey > 3) {
            arrayList = new ArrayList<>(setList);
            char c = map1.get(maxKey);
            for (String t : arrayList) {
                char[] chars = t.toCharArray();
                if (chars[0] == c) {
                    setList.remove(t);
                }
            }
            setList2.add(String.valueOf(map1.get(maxKey)));
            countChar = 0;
            map1 = new LinkedHashMap<>();
            return arrayList;
        }
        countChar = 0;
        return listGdeSovpadenia;
    }

    // проверка после результрующего склеивания второй буквы
    public static List<String> validTwoChar(List<String> list) {

        List<String> listGdeSovpadenia = new ArrayList<>();
        char[] mainChar = text.toCharArray();
        for (String s : list) {
            char[] chars = s.toCharArray();
            if (mainChar[1] == chars[1]) {
                countChar++;
                map2.put(countChar, mainChar[1]);
            } else {
                listGdeSovpadenia.add(s);
            }
        }
        int maxKey = 0;
        for (Map.Entry<Integer, Character> pair : map1.entrySet()) {
            int key = pair.getKey();
            if (maxKey < key) {
                maxKey = key;
            }
        }
        if (maxKey > 3) {
            arrayList = new ArrayList<>(setList);

            char c = map1.get(maxKey);
            for (String t : setList) {
                char[] chars = t.toCharArray();
                if (chars[0] == c) {
                    arrayList.remove(t);
                }
            }
            setList2.add(String.valueOf(map1.get(maxKey)));
            countChar = 0;
            map1 = new LinkedHashMap<>();
            return arrayList;
        }
        countChar = 0;
        return listGdeSovpadenia;
    }


    public static void QuineMethod(String s) {
        List<String> list = Arrays.asList(s.split(" v "));
        List<String> list2 = new ArrayList<>(list);
        text = list.get(count);

        if (text.length() == 2) {
            if (list.size() == 2) {
                while (true) {
                    text = list.get(count);
                    list = validOneChar(list);
                    if (list.size() == 0) {
                        break;
                    }
                    text = list.get(count);
                }
                while (true) {
                    text = list2.get(count);
                    list2 = validTwoChar(list2);
                    if (list2.size() == 0) {
                        break;
                    }
                    text = list2.get(count);
                }
            } else if (list.size() > 2) {
                while (true) {

                    text = list.get(count);
                    list = validOneChar(list);
                    if (list.size() == 0) {
                        break;
                    }
                    text = list.get(count);
                }
                while (true) {
                    if (arrayList.size() == 0) {
                        text = list2.get(count);
                        list2 = validTwoChar(list2);
                        if (list2.size() == 0) {
                            break;
                        }
                        text = list2.get(count);
                    } else {
                        text = arrayList.get(count);
                        arrayList = validTwoChar(arrayList);
                        if (arrayList.size() == 0) {
                            break;
                        }
                        text = arrayList.get(count);
                    }
                }
            }
            int maxKey1 = 0;
            for (Map.Entry<Integer, Character> pair : map1.entrySet()) {

                int key = pair.getKey();

                if (maxKey1 < key) {
                    maxKey1 = key;
                }
            }
            int maxKey2 = 0;
            for (Map.Entry<Integer, Character> pair : map2.entrySet()) {

                int key = pair.getKey();

                if (maxKey2 < key) {
                    maxKey2 = key;
                }
            }
            ArrayList<String> arrayList = new ArrayList<>(setList);
            if (maxKey1 < maxKey2) {
                char c = map2.get(maxKey2);
                for (String t : setList) {
                    char[] chars = t.toCharArray();
                    if (chars[1] == c) {
                        arrayList.remove(t);
                    }
                }
                setList2.add(String.valueOf(map2.get(maxKey2)));
                setList2.addAll(arrayList);
                arrayList = new ArrayList<>();
            }
            if (maxKey1 > maxKey2) {
                char c = map1.get(maxKey1);
                for (String t : setList) {
                    char[] chars = t.toCharArray();
                    if (chars[0] == c) {
                        arrayList.remove(t);
                    }
                }
                setList2.add(String.valueOf(map1.get(maxKey1)));
                arrayList = new ArrayList<>();
            }
            if (maxKey1 == maxKey2) {
                if (arrayList.size() == 2) {
                    if (!arrayList.get(0).equals(arrayList.get(1))) {
                        setList2.addAll(arrayList);
                    }
                } else {
                    char c = map1.get(maxKey1);
                    for (String t : setList) {
                        char[] chars = t.toCharArray();
                        if (chars[0] == c) {
                            arrayList.remove(t);
                        }
                    }
                    setList2.add(String.valueOf(map1.get(maxKey1)));
                    setList2.addAll(arrayList);
                    c = map2.get(maxKey2);
                    for (String t : arrayList) {
                        char[] chars = t.toCharArray();
                        if (chars[1] == c) {
                            arrayList.remove(t);
                        }
                    }
                    setList2.add(String.valueOf(map2.get(maxKey2)));
                }
            }
        } else {


            for (int i = 0; i < list.size(); i++) {
                String s1 = list.get(i);
                char[] chars1 = s1.toCharArray();

                for (int k = 0; k < list.size(); k++) {
                    String s2 = list.get(k);
                    if (s1.equals(s2)) {
                        if (i < list.size() - 1) {
                            k++;
                            s2 = list.get(k);
                        } else {
                            continue;
                        }
                    }
                    char[] chars2 = s2.toCharArray();
                    String oneSkleyka = "";
                    for (int j = 0; j < chars1.length; j++) {
                        if (chars1[j] == chars2[j]) {
                            oneSkleyka += chars1[j];

                        }
                        if (oneSkleyka.length() == chars1.length - 1) {
                            if (isValidString(oneSkleyka)) {
                                setList.add(oneSkleyka);
                                oneSkleyka = "";
                            }
                        }
                    }

                }
            }
        }
    }

    public static boolean isValidString(String s) {
        for (String getSet : setList) {
            return !s.equals(getSet);
        }
        return true;
    }

    public static StringBuilder SDNF(String vec, String characterString) {
        StringBuilder result = new StringBuilder();
        char[] arrVec = vec.toCharArray();
        char[] arrChar = characterString.toCharArray();
        for (int j = 0; j < arrVec.length; j++) {
            if (arrVec[j] == '0') {
                result.append(arrChar[j]);
            } else {
                String upperCase = String.valueOf(arrChar[j]);
                result.append(upperCase.toUpperCase());
            }
        }
        return result.append(" v ");
    }
}
