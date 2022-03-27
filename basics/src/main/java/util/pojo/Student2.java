package util.pojo;

import java.util.Objects;

public class Student2 {

    private int grade;
    private int cls;
    private String firstName;
    private String lastName;

    public Student2(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * 当哈希冲突时, 会调用 equals 判断 2 个对象是否相等
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student2 another = (Student2) o;
        return grade == another.grade &&
                cls == another.cls &&
                Objects.equals(firstName.toLowerCase(), another.firstName.toLowerCase()) &&
                Objects.equals(lastName.toLowerCase(), another.lastName.toLowerCase());
    }

    /**
     * 对整型溢出不做处理, 字符不区分大小写, Object 中的 hashCode 根据地址计算
     */
    @Override
    public int hashCode() {
        int B = 31;

        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();

        return hash;
    }
}
