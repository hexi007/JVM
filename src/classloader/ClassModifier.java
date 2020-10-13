package classloader;

/**
 * @author 27771
 * @description 将java.lang.System替换为我们自己定义的HackSystem类，暂时只提供修改常量池的功能
 * @create 2020-10-12 21:06
 **/
public class ClassModifier {

    /**
    * CONSTANT_POOL_COUNT_INDEX Class: 文件中常量池的起始偏移，8个字节
    */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * CONSTANT_Utf8_info: 常量的tag标志
     */
    private static final int CONSTANT_UTF8_INFO = 1;

    /**
     * CONSTANT_ITEM_LENGTH: 常量池11种变量所占长度，CONSTANT_UTF8_INFO除外，因为它不定长
     */
    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9 , 3, 3 ,5, 5, 5 ,5};

    /**
     * U1表示占一个字节，U1表示占两个字节
     */
    private static final int U1 = 1;
    private static final int U2 = 2;

    /**
     * classByte: 字节数组
     */
    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    /**
    * @Description  修改常量池中CONSTANT_UTF8_INFO常量的内容
    * @Param  oldString 修改前的字符串
    * @Param  newString 修改后的字符串
    * @return  修改结果
    */
    public byte[] modifyUTF8Constant (String oldStr, String newStr){
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + U2;
        for(int i = 0; i < cpc; i++){
            int tag = ByteUtils.bytes2Int(classByte, offset , U1);
            if(tag == CONSTANT_UTF8_INFO) {
                int len = ByteUtils.bytes2Int(classByte, offset + U1 , U2);
                offset += (U1 + U2);
                String str = ByteUtils.bytes2String(classByte, offset, len);
                if(str.equalsIgnoreCase(oldStr)){
                    byte[] strBytes = ByteUtils.string2Bytes(newStr);
                    byte[] strLen = ByteUtils.int2Bytes(newStr.length(), U2);
                    classByte = ByteUtils.bytesReplace(classByte, offset - U2, U2, strLen);
                    classByte = ByteUtils.bytesReplace(classByte, offset, len, strBytes);
                    return classByte;
                } else {
                    offset += len;
                }
            } else {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

    /**
     * 获取常量池中常量的数量
     * @return 常量池数量
     */
    private int getConstantPoolCount() {
        return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, U2);
    }
}