package com.nguyenthanhchung.carop2p;

/**
 * Created by Tran Nhut Cuong on 14/04/2018.
 */

public class Player {
    private int BanCo[][];
    private int SoCot;
    private int SoHang;

    public Player(int soCot, int soHang){
        BanCo = new int[soHang][soCot];
        for(int i = 0; i < SoHang; ++i)
            for(int j = 0; j < SoCot; ++j){
                BanCo[i][j] = 0;
            }
        SoCot = soCot;
        SoHang = soHang;
    }

    // Hàm reset bàn cờ
    public void TaoLaiBanCo(){
        for(int i = 0; i < SoHang; ++i)
            for(int j = 0; j < SoCot; ++j){
                BanCo[i][j] = 0;
            }
    }

    /* Hàm đánh 1 quân cờ vào bàn cờ: vitriX, vitriY: Tọa độ
                                      NguoiChoi(1: Người chơi 1, 2: Người chơi 2) */
    public boolean DanhCo(int vitriX, int vitriY, int NguoiChoi){

        // False: đã có quân cờ; tọa độ truyền vào sai
        if(BanCo[vitriX][vitriY] != 0 || KiemTraTrongBanCo(vitriX, vitriY)==false)
            return false;
        else{
            if(NguoiChoi == 1)
                BanCo[vitriX][vitriY] = 1;
            else if(NguoiChoi == 2)
                BanCo[vitriX][vitriY] = 2;
        }
        return true;
    }

    // Kiểm tra 1 tọa độ có trong bàn cơ hay không
    public boolean KiemTraTrongBanCo(int vitriX, int vitriY){
        if(vitriX >= 0 && vitriX < SoHang && vitriY >= 0 && vitriY < SoCot)
            return true;
        return false;
    }

    /* Kiểm tra kết thúc: trả về 0: chưa kết thúc;
                                 1: người chơi 1 thắng;
                                 2: người chơi 2 thắng  */
    public int KiemTraKetThuc(){
        int i, j, k;
        int[] dx =  { 1, 1, 1, 0 };
        int[] dy =  { -1, 0, 1, 1 };

        for (i = 0; i < SoHang; i++)
            for (j = 0; j < SoCot; j++)
            {
                if (BanCo[i][j] != 0)
                {
                    for (k = 0; k < 4; k++)
                    {
                        int count = 0, x = i, y = j;
                        while (count < 5 && this.KiemTraTrongBanCo(x, y) && BanCo[x][y] == BanCo[i][j])
                        {
                            count++;
                            x += dx[k];
                            y += dy[k];
                        }

                        if (count == 5 && BanCo[i][j]==1)
                        {
                            return 1;
                        }
                        else if(count == 5 && BanCo[i][j]==2)
                        {
                            return 2;
                        }
                    }
                }
            }
        return 0;
    }
}
