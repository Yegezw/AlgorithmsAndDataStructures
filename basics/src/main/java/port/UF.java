package port;

public interface UF {

    int getSize();

    /**
     * 查看元素 p 和元素 q 是否所属一个集合
     */
    boolean isConnected(int p, int q);

    /**
     * 合并元素 p 和元素 q 所属的集合
     */
    void unionElements(int p, int q);
}
