package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhengwu
 * @version 1.0
 * @description
 * @date 2024/3/19 12:05 AM
 */
public class Num68_TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<String>> wordsList = new ArrayList<>();
        int rowCount = 0;
        List<String> rowWordList = new ArrayList<>();
        List<Integer> rowCountList = new ArrayList<>();
        for (String s: words) {
            if (rowCount + rowWordList.size() + s.length() > maxWidth) {
                wordsList.add(new ArrayList<>(rowWordList));
                rowCountList.add(rowCount);
                rowCount = 0;
                rowWordList.clear();
            }
            rowCount += s.length();
            rowWordList.add(s);
        }
        if (!rowWordList.isEmpty()) {
            wordsList.add(rowWordList);
            rowCountList.add(rowCount);
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < wordsList.size() - 1; i++) {
            List<String> rowWords = wordsList.get(i);
            // 1行1个
            if (rowWords.size() == 1) {
                int spaceWidth = maxWidth - rowCountList.get(i);
                StringBuilder spaceBuilder = new StringBuilder();
                for (int k = 0; k < spaceWidth; k++) {
                    spaceBuilder.append(" ");
                }
                res.add(rowWords.get(0) + spaceBuilder.toString());
            } else {
                int spaceNum = rowWords.size() - 1;
                int spaceWidth = maxWidth - rowCountList.get(i);
                int each = spaceWidth / spaceNum;
                StringBuilder spaceBuilder = new StringBuilder();
                for (int k = 0; k < each; k++) {
                    spaceBuilder.append(" ");
                }
                String space = spaceBuilder.toString();
                int other = spaceWidth - each * spaceNum;
                StringBuilder builder = new StringBuilder();
                builder.append(rowWords.get(0));
                for (int j = 1; j < rowWords.size(); j++) {
                    builder.append(space);
                    if (j <= other) {
                        builder.append(" ");
                    }
                    builder.append(rowWords.get(j));
                }
                res.add(builder.toString());
            }

        }
        List<String> lastWords = wordsList.get(wordsList.size() - 1);
        StringBuilder builder = new StringBuilder();
        builder.append(lastWords.get(0));
        for (int i = 1; i < lastWords.size(); i++){
            builder.append(" ").append(lastWords.get(i));
        }
        int other = maxWidth - rowCountList.get(wordsList.size() - 1) - lastWords.size() + 1;
        for (int i = 0; i < other; i++) {
            builder.append(" ");
        }
        res.add(builder.toString());
        return res;
    }

    public static void main(String[] args) {
        String[] strings = {"This", "is", "an", "example", "of", "text", "justification."};
        Num68_TextJustification num68TextJustification = new Num68_TextJustification();
        num68TextJustification.fullJustify(strings, 16);
    }
}
