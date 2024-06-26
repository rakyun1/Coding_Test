package Programers.kakao.fail;

import java.io.IOException;

public class PedestrianHeaven {
    static Element[][] dp;
    static int MOD = 20170805;
    static int answer;
    static int maxY;
    static int maxX;
    public static void main(String[] args) throws IOException {
        int m = 3;

//        int n = 3;
        int n = 6;

//        int[][] cityMap = new int[][]{
//                {0, 0, 0},
//                {0, 0, 0},
//                {0, 0, 0}
//        };

        int[][] cityMap = new int[][]{
                {0, 2, 0, 0, 0, 2},
                {0, 0, 2, 0, 1, 0},
                {1, 0, 0, 2, 2, 0}
        };

        System.out.println(solution(m, n, cityMap));
    }
    public static int solution(int m, int n, int[][] cityMap) {
        dp = new Element[cityMap.length][cityMap[0].length];
        maxY = m;
        maxX = n;
        answer = 0;

        dp[0][0] = new Element(0,0);
        for (int i = 1; i < cityMap[0].length; i++) {
            if (cityMap[0][i] != 1) {
                if (cityMap[0][i] == 2) {
                    dp[0][i] = new Element(0, 1);
                } else {
                    dp[0][i] = new Element(1, 1);
                }
            } else {
                dp[0][i] = new Element(0,0);
            }
        }

        for (int i = 1; i < cityMap.length; i++) {
            if (cityMap[i][0] != 1) {
                if (cityMap[i][0] == 2) {
                    dp[i][0] = new Element(1, 0);
                } else {
                    dp[i][0] = new Element(1, 1);
                }
            } else {
                dp[i][0] = new Element(0,0);
            }
        }


        searchPath(new int[]{0,0}, new int[]{0,0}, cityMap);

        return answer % MOD;
    }

    private static void searchPath(int[] current, int[] pastWay, int[][] cityMap){
        int currentY = current[0];
        int currentX = current[1];
        if (currentY == maxY - 1 && currentX == maxX - 1) {
            answer++;
        } else {
            if (cityMap[currentY][currentX] == 2) {
                if (currentY + pastWay[0] < maxY && currentX + pastWay[1] < maxX && cityMap[currentY + pastWay[0]][currentX + pastWay[1]] != 1) {
                    searchPath(new int[]{currentY + pastWay[0], currentX + pastWay[1]}, pastWay, cityMap);
                }
            } else {
                // y축 이동
                if (currentY + 1 < maxY && cityMap[currentY + 1][currentX] != 1) {
                    searchPath(new int[]{currentY + 1, currentX}, new int[]{1, 0}, cityMap);
                }

                // x축 이동
                if (currentX + 1 < maxX && cityMap[currentY][currentX + 1] != 1) {
                    searchPath(new int[]{currentY, currentX + 1}, new int[]{0, 1}, cityMap);
                }
            }
        }
    }
}

class Element {
    int possibleY;
    int possibleX;

    public Element(int y, int x) {
        possibleY = y;
        possibleX = x;
    }
}
