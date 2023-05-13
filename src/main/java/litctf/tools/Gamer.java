package litctf.tools;

import com.alibaba.fastjson2.JSON;

import java.io.*;
import java.util.List;

public class Gamer {
    private String name;
    private String password;
    private Boolean isLogin;
    public Gamer(String name, String password) {
        this.name = name;
        this.password=password;
    }


    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    static public List<Gamer> getGamers() throws IOException {
        FileReader reader = new FileReader("gamers.json");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String json = bufferedReader.readLine();
        bufferedReader.close();
        reader.close();
        List<Gamer> gamers = JSON.parseArray(json, Gamer.class);
        return gamers;
    }
    static public boolean saveGamers(List<Gamer> gamers) throws IOException {
        FileWriter fileWriter = new FileWriter("gamers.json");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String jsonStr = JSON.toJSONString(gamers);
        bufferedWriter.write(jsonStr);
        bufferedWriter.flush();
        bufferedWriter.close();
        return true;
    }
}
