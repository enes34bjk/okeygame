import java.util.Arrays;
import java.util.Random;

public class okey {
    private int[] taslar= new int[106]; //Array for storing all the numbers

    private int[] o1taslar= new int[15];//
    private int[] o2taslar= new int[15];//Arrays for player's numbers
    private int[] o3taslar= new int[15];//
    private int[] o4taslar= new int[15];//

    private int okeytasi =0;

    public static void main(String Args[]){
        okey oyun=new okey();
        oyun.oyunOyna();
    }

    //Method for shuffling the numbers and giving to the players
    public void oyunOyna(){

        int[][] players={o1taslar,o2taslar,o3taslar,o4taslar};

        /////////////// Putting all numbers to the array////////////////
        int y=0;
        int a=0;
        while(y<=105){
            taslar[y]=a;
            taslar[y+1]=a;
            a=a+1;
            y=y+2;
        }
        /////////////////////////////

        //Shuffling the numbers before distributing to the players
        Random rand = new Random();
        for (int i = 0; i < taslar.length; i++) {
            int randomIndexToSwap = rand.nextInt(taslar.length);
            int temp = taslar[randomIndexToSwap];
            taslar[randomIndexToSwap] = taslar[i];
            taslar[i] = temp;
        }
        /////////////////////////////////

        //Choosing the random number
        int randomPosition=rand.nextInt(taslar.length);
        int randomNumber=taslar[randomPosition];

        while (randomNumber==52){ //if the random number is 52
            randomPosition=rand.nextInt(taslar.length);
            randomNumber=taslar[randomPosition];
        }
        if (randomNumber==12||randomNumber==25||randomNumber==38||randomNumber==51){ //if needed to go to beginning number
            randomNumber=randomNumber-12;
        }else{
            randomNumber+=1;
        }
        okeytasi=randomNumber;

        ///////////////////////////

        //distrubuting the numbers among players
        for (int i = 0; i < players.length; i++) {//shuffling the players to get random person for 15 numbers
            int randomIndexToSwap = rand.nextInt(players.length);
            int temp[] = players[randomIndexToSwap];
            players[randomIndexToSwap] = players[i];
            players[i] = temp;
        }

        for (int i=0;i<15;i++){
            players[0][i]=taslar[i];
        }
        for (int i=15;i<29;i++){
            players[1][i-15]=taslar[i];
        }
        for (int i=29;i<43;i++){
            players[2][i-29]=taslar[i];
        }
        for (int i=43;i<57;i++){
            players[3][i-43]=taslar[i];
        }

        for (int i=0;i<4;i++){ //to not mixed up 14 players with others
            if (players[i][15-1]==0){
                int[] temp=new int[14];
                for (int sayı=0;sayı<players[i].length-1;sayı++){
                    temp[sayı]=players[i][sayı];
                }
                players[i]=temp;
            }
            Arrays.sort(players[i]);
        }
        ////////////////////////////////
        //Showing the results
        double[] sapmalar=new double[4];
        System.out.print("Oyuncu Eli "+Arrays.toString(players[0])+" Standart Sapması: ");
        sapmalar[0]=standartSapma(players[0]);
        System.out.println(sapmalar[0]);
        System.out.print("Oyuncu Eli "+Arrays.toString(players[1])+" Standart Sapması: ");
        sapmalar[1]=standartSapma(players[1]);
        System.out.println(sapmalar[1]);
        System.out.print("Oyuncu Eli "+Arrays.toString(players[2])+" Standart Sapması: ");
        sapmalar[2]=standartSapma(players[2]);
        System.out.println(sapmalar[2]);
        System.out.print("Oyuncu Eli "+Arrays.toString(players[3])+" Standart Sapması: ");
        sapmalar[3]=standartSapma(players[3]);
        System.out.println(sapmalar[3]);
        int bitmeyeenyakınel=enkucukbul(sapmalar);
        System.out.println("");
        System.out.println("Bitmeye en yakın el: "+ Arrays.toString(players[bitmeyeenyakınel]));
    }
    public double standartSapma(int sayılar[])
    {
        double toplam = 0.0, standartsapma = 0.0;
        int uzunluk;
        if (sayılar[sayılar.length-1]==100){
            uzunluk= sayılar.length-1;
        }else {
            uzunluk= sayılar.length;
        }

        for(int i=0;i<sayılar.length;i++) {
            if (sayılar[i]!=100){
                toplam += sayılar[i];
            }
        }
        double ortalama = toplam/uzunluk;

        for(int i=0;i<sayılar.length;i++) {
            if (sayılar[i]!=100){
                standartsapma += Math.pow(sayılar[i] - ortalama, 2);
            }
        }
        return Math.sqrt(standartsapma/uzunluk);
    }
    public  int enkucukbul(double [] arr1) {
        int index = 0;
        double min = arr1[index];

        for (int i=1; i<arr1.length; i++) {
            if (arr1[i] < min) {
                min = arr1[i];
                index = i;
            }

        }
        return index;
    }
}
