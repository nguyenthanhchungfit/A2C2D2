package com.nguyenthanhchung.carop2p.model;

/**
 * Created by Tran Nhut Cuong on 14/04/2018.
 */

public class Player {
    private boolean BanCo[][];
    private int SoCot = 15;
    private int SoHang = 15;
    private boolean id;

    public Player(){
        BanCo = new boolean[SoHang][SoCot];
        for(int i = 0; i < SoHang; ++i) {
            for (int j = 0; j < SoCot; ++j) {
                BanCo[i][j] = false;
            }
        }
        id = false;
    }

    public boolean getId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    // Hàm reset bàn cờ
    public void TaoLaiBanCo(){
        for(int i = 0; i < SoHang; ++i)
            for(int j = 0; j < SoCot; ++j){
                BanCo[i][j] = false;
            }
    }

    public boolean SetOCo(int position)
    {
        int toaDoY = position/SoCot;
        int toaDoX = position%SoCot;
        if(KiemTraTrongBanCo(toaDoX, toaDoY)==false)
            return false;
        else
            BanCo[toaDoX][toaDoY] = true;
        return true;
    }

    // Kiểm tra 1 tọa độ có trong bàn cơ hay không
    public boolean KiemTraTrongBanCo(int vitriX, int vitriY){
        if(vitriX >= 0 && vitriX < SoHang && vitriY >= 0 && vitriY < SoCot)
            return true;
        return false;
    }

    /* Kiểm tra kết thúc: trả về false: chưa kết thúc;
                                 true: kết thúc; */
    public boolean KiemTraKetThuc(){
        int i, j, k;
        int[] dx =  { 1, 1, 1, 0 };
        int[] dy =  { -1, 0, 1, 1 };

        for (i = 0; i < SoHang; i++)
            for (j = 0; j < SoCot; j++)
            {
                if (BanCo[i][j]==true)
                {
                    for (k = 0; k < 4; k++)
                    {
                        int count = 0, x = i, y = j;
                        while (count < 5 && this.KiemTraTrongBanCo(x, y) && BanCo[x][y] == true)
                        {
                            count++;
                            x += dx[k];
                            y += dy[k];
                        }

                        if (count == 5)
                        {
                            return true;
                        }
                    }
                }
            }
        return false;
    }


}
