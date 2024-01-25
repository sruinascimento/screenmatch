package br.com.rsfot.screenmatch.util;

public interface IConvert {
    <T> T getData(String json, Class<T> clazz);
}
