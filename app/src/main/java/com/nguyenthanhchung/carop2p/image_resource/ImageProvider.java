package com.nguyenthanhchung.carop2p.image_resource;

import com.nguyenthanhchung.carop2p.R;
import com.nguyenthanhchung.carop2p.model.CellBoard;
import com.nguyenthanhchung.carop2p.model.ImageEmotion;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nguyen Thanh Chung on 2018-04-27.
 */

public class ImageProvider {
    private static int MAX_CELLS = 255;
    // EMOTION IMAGE LIST
    private static ArrayList<ImageEmotion> EMOTION_IMAGE_LIST;
    private static void initEMOTION_IMAGE_LIST(){
        EMOTION_IMAGE_LIST = new ArrayList<>();
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo1));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo2));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo3));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo4));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo5));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo6));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo7));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo8));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo9));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo10));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo11));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo12));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo13));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo14));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo15));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo16));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo17));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo18));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo19));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo20));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo21));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo22));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo23));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo24));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo25));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo26));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo27));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo28));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo29));
        EMOTION_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo30));
    }

    private static ArrayList<CellBoard> CELL_BOARD_IMAGE_LIST;
    private static void initCELL_BOARD_IMAGE_LIST(){
        CELL_BOARD_IMAGE_LIST = new ArrayList<>();
        for(int i =0; i< MAX_CELLS; i++){
            CELL_BOARD_IMAGE_LIST.add(new CellBoard(R.drawable.ic_empty_cell, false));
        }
    }

    private static ArrayList<ImageEmotion> EMOTION_128_IMAGE_LIST;
    private static void initEMOTION_128_IMAGE_LIST(){
        EMOTION_128_IMAGE_LIST = new ArrayList<>();
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_1));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_2));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_3));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_4));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_5));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_6));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_7));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_8));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_9));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_10));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_11));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_12));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_13));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_14));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_15));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_16));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_17));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_18));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_19));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_20));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_21));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_22));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_23));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_24));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_25));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_26));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_27));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_28));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_29));
        EMOTION_128_IMAGE_LIST.add(new ImageEmotion(R.drawable.emo_128_30));
    }

    static{
        initEMOTION_IMAGE_LIST();

        initCELL_BOARD_IMAGE_LIST();

        initEMOTION_128_IMAGE_LIST();
    }

    public static ArrayList<ImageEmotion> getEMOTION_IMAGE_LIST(){
        return EMOTION_IMAGE_LIST;
    }

    public static ArrayList<CellBoard> getCELL_IMAGE_LIST(){
        return CELL_BOARD_IMAGE_LIST;
    }

    public static ArrayList<ImageEmotion> getEMOTION_128_IMAGE_LIST(){
        return EMOTION_128_IMAGE_LIST;
    }
}
