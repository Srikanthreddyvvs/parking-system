package com.practice.Emp.EmpService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;

public class QR {
    public static byte[] qr(String s,int w,int h){
        try{
            BitMatrix m = new MultiFormatWriter().encode(s, BarcodeFormat.QR_CODE,w,h);
            ByteArrayOutputStream opt = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(m,"PNG",opt);
            return opt.toByteArray();
        }catch(Exception e){
            throw new RuntimeException("unable to generate QR");
        }
    }
}
