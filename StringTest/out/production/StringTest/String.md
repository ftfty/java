```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package java.lang;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.StringCoding.Result;
import java.lang.StringLatin1.CharsSpliterator;
import java.lang.StringUTF16.CodePointsSpliterator;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.Spliterator.OfInt;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.HotSpotIntrinsicCandidate;
import jdk.internal.vm.annotation.Stable;

public final class String implements Serializable, Comparable<String>, CharSequence {
    @Stable
    private final byte[] value;
    private final byte coder;
    private int hash;
    private static final long serialVersionUID = -6849794470754667710L;
    static final boolean COMPACT_STRINGS = true;
    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];
    public static final Comparator<String> CASE_INSENSITIVE_ORDER = new String.CaseInsensitiveComparator();
    static final byte LATIN1 = 0;
    static final byte UTF16 = 1;

    public String() {
        this.value = "".value;
        this.coder = "".coder;
    }

    @HotSpotIntrinsicCandidate
    public String(String original) {
        this.value = original.value;
        this.coder = original.coder;
        this.hash = original.hash;
    }

    public String(char[] value) {
        this((char[])value, 0, value.length, (Void)null);
    }

    public String(char[] value, int offset, int count) {
        this(value, offset, count, rangeCheck(value, offset, count));
    }

    private static Void rangeCheck(char[] value, int offset, int count) {
        checkBoundsOffCount(offset, count, value.length);
        return null;
    }

    public String(int[] codePoints, int offset, int count) {
        checkBoundsOffCount(offset, count, codePoints.length);
        if (count == 0) {
            this.value = "".value;
            this.coder = "".coder;
        } else {
            if (COMPACT_STRINGS) {
                byte[] val = StringLatin1.toBytes(codePoints, offset, count);
                if (val != null) {
                    this.coder = 0;
                    this.value = val;
                    return;
                }
            }

            this.coder = 1;
            this.value = StringUTF16.toBytes(codePoints, offset, count);
        }
    }

    /** @deprecated */
    @Deprecated(
        since = "1.1"
    )
    public String(byte[] ascii, int hibyte, int offset, int count) {
        checkBoundsOffCount(offset, count, ascii.length);
        if (count == 0) {
            this.value = "".value;
            this.coder = "".coder;
        } else {
            if (COMPACT_STRINGS && (byte)hibyte == 0) {
                this.value = Arrays.copyOfRange(ascii, offset, offset + count);
                this.coder = 0;
            } else {
                hibyte <<= 8;
                byte[] val = StringUTF16.newBytesFor(count);

                for(int i = 0; i < count; ++i) {
                    StringUTF16.putChar(val, i, hibyte | ascii[offset++] & 255);
                }

                this.value = val;
                this.coder = 1;
            }

        }
    }

    /** @deprecated */
    @Deprecated(
        since = "1.1"
    )
    public String(byte[] ascii, int hibyte) {
        this(ascii, hibyte, 0, ascii.length);
    }

    public String(byte[] bytes, int offset, int length, String charsetName) throws UnsupportedEncodingException {
        if (charsetName == null) {
            throw new NullPointerException("charsetName");
        } else {
            checkBoundsOffCount(offset, length, bytes.length);
            Result ret = StringCoding.decode(charsetName, bytes, offset, length);
            this.value = ret.value;
            this.coder = ret.coder;
        }
    }

    public String(byte[] bytes, int offset, int length, Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        } else {
            checkBoundsOffCount(offset, length, bytes.length);
            Result ret = StringCoding.decode(charset, bytes, offset, length);
            this.value = ret.value;
            this.coder = ret.coder;
        }
    }

    public String(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        this((byte[])bytes, 0, bytes.length, (String)charsetName);
    }

    public String(byte[] bytes, Charset charset) {
        this((byte[])bytes, 0, bytes.length, (Charset)charset);
    }

    public String(byte[] bytes, int offset, int length) {
        checkBoundsOffCount(offset, length, bytes.length);
        Result ret = StringCoding.decode(bytes, offset, length);
        this.value = ret.value;
        this.coder = ret.coder;
    }

    public String(byte[] bytes) {
        this((byte[])bytes, 0, bytes.length);
    }

    public String(StringBuffer buffer) {
        this(buffer.toString());
    }

    public String(StringBuilder builder) {
        this((AbstractStringBuilder)builder, (Void)null);
    }

    public int length() {
        return this.value.length >> this.coder();
    }

    public boolean isEmpty() {
        return this.value.length == 0;
    }

    public char charAt(int index) {
        return this.isLatin1() ? StringLatin1.charAt(this.value, index) : StringUTF16.charAt(this.value, index);
    }

    public int codePointAt(int index) {
        if (this.isLatin1()) {
            checkIndex(index, this.value.length);
            return this.value[index] & 255;
        } else {
            int length = this.value.length >> 1;
            checkIndex(index, length);
            return StringUTF16.codePointAt(this.value, index, length);
        }
    }

    public int codePointBefore(int index) {
        int i = index - 1;
        if (i >= 0 && i < this.length()) {
            return this.isLatin1() ? this.value[i] & 255 : StringUTF16.codePointBefore(this.value, index);
        } else {
            throw new StringIndexOutOfBoundsException(index);
        }
    }

    public int codePointCount(int beginIndex, int endIndex) {
        if (beginIndex >= 0 && beginIndex <= endIndex && endIndex <= this.length()) {
            return this.isLatin1() ? endIndex - beginIndex : StringUTF16.codePointCount(this.value, beginIndex, endIndex);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        if (index >= 0 && index <= this.length()) {
            return Character.offsetByCodePoints(this, index, codePointOffset);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        checkBoundsBeginEnd(srcBegin, srcEnd, this.length());
        checkBoundsOffCount(dstBegin, srcEnd - srcBegin, dst.length);
        if (this.isLatin1()) {
            StringLatin1.getChars(this.value, srcBegin, srcEnd, dst, dstBegin);
        } else {
            StringUTF16.getChars(this.value, srcBegin, srcEnd, dst, dstBegin);
        }

    }

    /** @deprecated */
    @Deprecated(
        since = "1.1"
    )
    public void getBytes(int srcBegin, int srcEnd, byte[] dst, int dstBegin) {
        checkBoundsBeginEnd(srcBegin, srcEnd, this.length());
        Objects.requireNonNull(dst);
        checkBoundsOffCount(dstBegin, srcEnd - srcBegin, dst.length);
        if (this.isLatin1()) {
            StringLatin1.getBytes(this.value, srcBegin, srcEnd, dst, dstBegin);
        } else {
            StringUTF16.getBytes(this.value, srcBegin, srcEnd, dst, dstBegin);
        }

    }

    public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
        if (charsetName == null) {
            throw new NullPointerException();
        } else {
            return StringCoding.encode(charsetName, this.coder(), this.value);
        }
    }

    public byte[] getBytes(Charset charset) {
        if (charset == null) {
            throw new NullPointerException();
        } else {
            return StringCoding.encode(charset, this.coder(), this.value);
        }
    }

    public byte[] getBytes() {
        return StringCoding.encode(this.coder(), this.value);
    }
    
//使用 null.equalse(object) 做比较会出现空指针异常!
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        } else {
            if (anObject instanceof String) {
                String aString = (String)anObject;
                if (this.coder() == aString.coder()) {
                    return this.isLatin1() ? StringLatin1.equals(this.value, aString.value) : StringUTF16.equals(this.value, aString.value);
                }
            }

            return false;
        }
    }

    public boolean contentEquals(StringBuffer sb) {
        return this.contentEquals((CharSequence)sb);
    }

    private boolean nonSyncContentEquals(AbstractStringBuilder sb) {
        int len = this.length();
        if (len != sb.length()) {
            return false;
        } else {
            byte[] v1 = this.value;
            byte[] v2 = sb.getValue();
            if (this.coder() == sb.getCoder()) {
                int n = v1.length;

                for(int i = 0; i < n; ++i) {
                    if (v1[i] != v2[i]) {
                        return false;
                    }
                }

                return true;
            } else {
                return !this.isLatin1() ? false : StringUTF16.contentEquals(v1, v2, len);
            }
        }
    }

    public boolean contentEquals(CharSequence cs) {
        if (cs instanceof AbstractStringBuilder) {
            if (cs instanceof StringBuffer) {
                synchronized(cs) {
                    return this.nonSyncContentEquals((AbstractStringBuilder)cs);
                }
            } else {
                return this.nonSyncContentEquals((AbstractStringBuilder)cs);
            }
        } else if (cs instanceof String) {
            return this.equals(cs);
        } else {
            int n = cs.length();
            if (n != this.length()) {
                return false;
            } else {
                byte[] val = this.value;
                if (this.isLatin1()) {
                    for(int i = 0; i < n; ++i) {
                        if ((val[i] & 255) != cs.charAt(i)) {
                            return false;
                        }
                    }
                } else if (!StringUTF16.contentEquals(val, cs, n)) {
                    return false;
                }

                return true;
            }
        }
    }

    public boolean equalsIgnoreCase(String anotherString) {
        return this == anotherString ? true : anotherString != null && anotherString.length() == this.length() && this.regionMatches(true, 0, anotherString, 0, this.length());
    }

    public int compareTo(String anotherString) {
        byte[] v1 = this.value;
        byte[] v2 = anotherString.value;
        if (this.coder() == anotherString.coder()) {
            return this.isLatin1() ? StringLatin1.compareTo(v1, v2) : StringUTF16.compareTo(v1, v2);
        } else {
            return this.isLatin1() ? StringLatin1.compareToUTF16(v1, v2) : StringUTF16.compareToLatin1(v1, v2);
        }
    }

    public int compareToIgnoreCase(String str) {
        return CASE_INSENSITIVE_ORDER.compare(this, str);
    }

    public boolean regionMatches(int toffset, String other, int ooffset, int len) {
        byte[] tv = this.value;
        byte[] ov = other.value;
        if (ooffset >= 0 && toffset >= 0 && (long)toffset <= (long)this.length() - (long)len && (long)ooffset <= (long)other.length() - (long)len) {
            if (this.coder() == other.coder()) {
                if (!this.isLatin1() && len > 0) {
                    toffset <<= 1;
                    ooffset <<= 1;
                    len <<= 1;
                }

                while(len-- > 0) {
                    if (tv[toffset++] != ov[ooffset++]) {
                        return false;
                    }
                }
            } else if (this.coder() == 0) {
                while(len-- > 0) {
                    if (StringLatin1.getChar(tv, toffset++) != StringUTF16.getChar(ov, ooffset++)) {
                        return false;
                    }
                }
            } else {
                while(len-- > 0) {
                    if (StringUTF16.getChar(tv, toffset++) != StringLatin1.getChar(ov, ooffset++)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) {
        if (!ignoreCase) {
            return this.regionMatches(toffset, other, ooffset, len);
        } else if (ooffset >= 0 && toffset >= 0 && (long)toffset <= (long)this.length() - (long)len && (long)ooffset <= (long)other.length() - (long)len) {
            byte[] tv = this.value;
            byte[] ov = other.value;
            if (this.coder() == other.coder()) {
                return this.isLatin1() ? StringLatin1.regionMatchesCI(tv, toffset, ov, ooffset, len) : StringUTF16.regionMatchesCI(tv, toffset, ov, ooffset, len);
            } else {
                return this.isLatin1() ? StringLatin1.regionMatchesCI_UTF16(tv, toffset, ov, ooffset, len) : StringUTF16.regionMatchesCI_Latin1(tv, toffset, ov, ooffset, len);
            }
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix, int toffset) {
        if (toffset >= 0 && toffset <= this.length() - prefix.length()) {
            byte[] ta = this.value;
            byte[] pa = prefix.value;
            int po = 0;
            int pc = pa.length;
            if (this.coder() == prefix.coder()) {
                int var7 = this.isLatin1() ? toffset : toffset << 1;

                while(po < pc) {
                    if (ta[var7++] != pa[po++]) {
                        return false;
                    }
                }
            } else {
                if (this.isLatin1()) {
                    return false;
                }

                while(po < pc) {
                    if (StringUTF16.getChar(ta, toffset++) != (pa[po++] & 255)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        return this.startsWith(prefix, 0);
    }

    public boolean endsWith(String suffix) {
        return this.startsWith(suffix, this.length() - suffix.length());
    }

    public int hashCode() {
        int h = this.hash;
        if (h == 0 && this.value.length > 0) {
            this.hash = h = this.isLatin1() ? StringLatin1.hashCode(this.value) : StringUTF16.hashCode(this.value);
        }

        return h;
    }

    public int indexOf(int ch) {
        return this.indexOf(ch, 0);
    }

    public int indexOf(int ch, int fromIndex) {
        return this.isLatin1() ? StringLatin1.indexOf(this.value, ch, fromIndex) : StringUTF16.indexOf(this.value, ch, fromIndex);
    }

    public int lastIndexOf(int ch) {
        return this.lastIndexOf(ch, this.length() - 1);
    }

    public int lastIndexOf(int ch, int fromIndex) {
        return this.isLatin1() ? StringLatin1.lastIndexOf(this.value, ch, fromIndex) : StringUTF16.lastIndexOf(this.value, ch, fromIndex);
    }

    public int indexOf(String str) {
        if (this.coder() == str.coder()) {
            return this.isLatin1() ? StringLatin1.indexOf(this.value, str.value) : StringUTF16.indexOf(this.value, str.value);
        } else {
            return this.coder() == 0 ? -1 : StringUTF16.indexOfLatin1(this.value, str.value);
        }
    }

    public int indexOf(String str, int fromIndex) {
        return indexOf(this.value, this.coder(), this.length(), str, fromIndex);
    }

    static int indexOf(byte[] src, byte srcCoder, int srcCount, String tgtStr, int fromIndex) {
        byte[] tgt = tgtStr.value;
        byte tgtCoder = tgtStr.coder();
        int tgtCount = tgtStr.length();
        if (fromIndex >= srcCount) {
            return tgtCount == 0 ? srcCount : -1;
        } else {
            if (fromIndex < 0) {
                fromIndex = 0;
            }

            if (tgtCount == 0) {
                return fromIndex;
            } else if (tgtCount > srcCount) {
                return -1;
            } else if (srcCoder == tgtCoder) {
                return srcCoder == 0 ? StringLatin1.indexOf(src, srcCount, tgt, tgtCount, fromIndex) : StringUTF16.indexOf(src, srcCount, tgt, tgtCount, fromIndex);
            } else {
                return srcCoder == 0 ? -1 : StringUTF16.indexOfLatin1(src, srcCount, tgt, tgtCount, fromIndex);
            }
        }
    }

    public int lastIndexOf(String str) {
        return this.lastIndexOf(str, this.length());
    }

    public int lastIndexOf(String str, int fromIndex) {
        return lastIndexOf(this.value, this.coder(), this.length(), str, fromIndex);
    }

    static int lastIndexOf(byte[] src, byte srcCoder, int srcCount, String tgtStr, int fromIndex) {
        byte[] tgt = tgtStr.value;
        byte tgtCoder = tgtStr.coder();
        int tgtCount = tgtStr.length();
        int rightIndex = srcCount - tgtCount;
        if (fromIndex > rightIndex) {
            fromIndex = rightIndex;
        }

        if (fromIndex < 0) {
            return -1;
        } else if (tgtCount == 0) {
            return fromIndex;
        } else if (srcCoder == tgtCoder) {
            return srcCoder == 0 ? StringLatin1.lastIndexOf(src, srcCount, tgt, tgtCount, fromIndex) : StringUTF16.lastIndexOf(src, srcCount, tgt, tgtCount, fromIndex);
        } else {
            return srcCoder == 0 ? -1 : StringUTF16.lastIndexOfLatin1(src, srcCount, tgt, tgtCount, fromIndex);
        }
    }

    public String substring(int beginIndex) {
        if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        } else {
            int subLen = this.length() - beginIndex;
            if (subLen < 0) {
                throw new StringIndexOutOfBoundsException(subLen);
            } else if (beginIndex == 0) {
                return this;
            } else {
                return this.isLatin1() ? StringLatin1.newString(this.value, beginIndex, subLen) : StringUTF16.newString(this.value, beginIndex, subLen);
            }
        }
    }

    public String substring(int beginIndex, int endIndex) {
        int length = this.length();
        checkBoundsBeginEnd(beginIndex, endIndex, length);
        int subLen = endIndex - beginIndex;
        if (beginIndex == 0 && endIndex == length) {
            return this;
        } else {
            return this.isLatin1() ? StringLatin1.newString(this.value, beginIndex, subLen) : StringUTF16.newString(this.value, beginIndex, subLen);
        }
    }

    public CharSequence subSequence(int beginIndex, int endIndex) {
        return this.substring(beginIndex, endIndex);
    }

    public String concat(String str) {
        if (str.isEmpty()) {
            return this;
        } else if (this.coder() == str.coder()) {
            byte[] val = this.value;
            byte[] oval = str.value;
            int len = val.length + oval.length;
            byte[] buf = Arrays.copyOf(val, len);
            System.arraycopy(oval, 0, buf, val.length, oval.length);
            return new String(buf, this.coder);
        } else {
            int len = this.length();
            int olen = str.length();
            byte[] buf = StringUTF16.newBytesFor(len + olen);
            this.getBytes(buf, 0, (byte)1);
            str.getBytes(buf, len, (byte)1);
            return new String(buf, (byte)1);
        }
    }

    public String replace(char oldChar, char newChar) {
        if (oldChar != newChar) {
            String ret = this.isLatin1() ? StringLatin1.replace(this.value, oldChar, newChar) : StringUTF16.replace(this.value, oldChar, newChar);
            if (ret != null) {
                return ret;
            }
        }

        return this;
    }

    public boolean matches(String regex) {
        return Pattern.matches(regex, this);
    }

    public boolean contains(CharSequence s) {
        return this.indexOf(s.toString()) >= 0;
    }

    public String replaceFirst(String regex, String replacement) {
        return Pattern.compile(regex).matcher(this).replaceFirst(replacement);
    }

    public String replaceAll(String regex, String replacement) {
        return Pattern.compile(regex).matcher(this).replaceAll(replacement);
    }

    public String replace(CharSequence target, CharSequence replacement) {
        String tgtStr = target.toString();
        String replStr = replacement.toString();
        int j = this.indexOf(tgtStr);
        if (j < 0) {
            return this;
        } else {
            int tgtLen = tgtStr.length();
            int tgtLen1 = Math.max(tgtLen, 1);
            int thisLen = this.length();
            int newLenHint = thisLen - tgtLen + replStr.length();
            if (newLenHint < 0) {
                throw new OutOfMemoryError();
            } else {
                StringBuilder sb = new StringBuilder(newLenHint);
                int i = 0;

                do {
                    sb.append(this, i, j).append(replStr);
                    i = j + tgtLen;
                } while(j < thisLen && (j = this.indexOf(tgtStr, j + tgtLen1)) > 0);

                return sb.append(this, i, thisLen).toString();
            }
        }
    }

    public String[] split(String regex, int limit) {
        char ch = 0;
        if ((regex.length() != 1 || ".$|()[{^?*+\\".indexOf(ch = regex.charAt(0)) != -1) && (regex.length() != 2 || regex.charAt(0) != '\\' || ((ch = regex.charAt(1)) - 48 | 57 - ch) >= 0 || (ch - 97 | 122 - ch) >= 0 || (ch - 65 | 90 - ch) >= 0) || ch >= '\ud800' && ch <= '\udfff') {
            return Pattern.compile(regex).split(this, limit);
        } else {
            int off = 0;
            int next = false;
            boolean limited = limit > 0;

            ArrayList list;
            int resultSize;
            int next;
            for(list = new ArrayList(); (next = this.indexOf(ch, off)) != -1; off = next + 1) {
                if (limited && list.size() >= limit - 1) {
                    resultSize = this.length();
                    list.add(this.substring(off, resultSize));
                    off = resultSize;
                    break;
                }

                list.add(this.substring(off, next));
            }

            if (off == 0) {
                return new String[]{this};
            } else {
                if (!limited || list.size() < limit) {
                    list.add(this.substring(off, this.length()));
                }

                resultSize = list.size();
                if (limit == 0) {
                    while(resultSize > 0 && ((String)list.get(resultSize - 1)).isEmpty()) {
                        --resultSize;
                    }
                }

                String[] result = new String[resultSize];
                return (String[])list.subList(0, resultSize).toArray(result);
            }
        }
    }

    public String[] split(String regex) {
        return this.split(regex, 0);
    }

    public static String join(CharSequence delimiter, CharSequence... elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        StringJoiner joiner = new StringJoiner(delimiter);
        CharSequence[] var3 = elements;
        int var4 = elements.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            CharSequence cs = var3[var5];
            joiner.add(cs);
        }

        return joiner.toString();
    }

    public static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        StringJoiner joiner = new StringJoiner(delimiter);
        Iterator var3 = elements.iterator();

        while(var3.hasNext()) {
            CharSequence cs = (CharSequence)var3.next();
            joiner.add(cs);
        }

        return joiner.toString();
    }

    public String toLowerCase(Locale locale) {
        return this.isLatin1() ? StringLatin1.toLowerCase(this, this.value, locale) : StringUTF16.toLowerCase(this, this.value, locale);
    }

    public String toLowerCase() {
        return this.toLowerCase(Locale.getDefault());
    }

    public String toUpperCase(Locale locale) {
        return this.isLatin1() ? StringLatin1.toUpperCase(this, this.value, locale) : StringUTF16.toUpperCase(this, this.value, locale);
    }

    public String toUpperCase() {
        return this.toUpperCase(Locale.getDefault());
    }

    public String trim() {
        String ret = this.isLatin1() ? StringLatin1.trim(this.value) : StringUTF16.trim(this.value);
        return ret == null ? this : ret;
    }

    public String strip() {
        String ret = this.isLatin1() ? StringLatin1.strip(this.value) : StringUTF16.strip(this.value);
        return ret == null ? this : ret;
    }

    public String stripLeading() {
        String ret = this.isLatin1() ? StringLatin1.stripLeading(this.value) : StringUTF16.stripLeading(this.value);
        return ret == null ? this : ret;
    }

    public String stripTrailing() {
        String ret = this.isLatin1() ? StringLatin1.stripTrailing(this.value) : StringUTF16.stripTrailing(this.value);
        return ret == null ? this : ret;
    }

    public boolean isBlank() {
        return this.indexOfNonWhitespace() == this.length();
    }

    private int indexOfNonWhitespace() {
        return this.isLatin1() ? StringLatin1.indexOfNonWhitespace(this.value) : StringUTF16.indexOfNonWhitespace(this.value);
    }

    public Stream<String> lines() {
        return this.isLatin1() ? StringLatin1.lines(this.value) : StringUTF16.lines(this.value);
    }

    public String toString() {
        return this;
    }

    public IntStream chars() {
        return StreamSupport.intStream((OfInt)(this.isLatin1() ? new CharsSpliterator(this.value, 1024) : new java.lang.StringUTF16.CharsSpliterator(this.value, 1024)), false);
    }

    public IntStream codePoints() {
        return StreamSupport.intStream((OfInt)(this.isLatin1() ? new CharsSpliterator(this.value, 1024) : new CodePointsSpliterator(this.value, 1024)), false);
    }

    public char[] toCharArray() {
        return this.isLatin1() ? StringLatin1.toChars(this.value) : StringUTF16.toChars(this.value);
    }

    public static String format(String format, Object... args) {
        return (new Formatter()).format(format, args).toString();
    }

    public static String format(Locale l, String format, Object... args) {
        return (new Formatter(l)).format(format, args).toString();
    }

    public static String valueOf(Object obj) {
        return obj == null ? "null" : obj.toString();
    }

    public static String valueOf(char[] data) {
        return new String(data);
    }

    public static String valueOf(char[] data, int offset, int count) {
        return new String(data, offset, count);
    }

    public static String copyValueOf(char[] data, int offset, int count) {
        return new String(data, offset, count);
    }

    public static String copyValueOf(char[] data) {
        return new String(data);
    }

    public static String valueOf(boolean b) {
        return b ? "true" : "false";
    }

    public static String valueOf(char c) {
        return COMPACT_STRINGS && StringLatin1.canEncode(c) ? new String(StringLatin1.toBytes(c), (byte)0) : new String(StringUTF16.toBytes(c), (byte)1);
    }

    public static String valueOf(int i) {
        return Integer.toString(i);
    }

    public static String valueOf(long l) {
        return Long.toString(l);
    }

    public static String valueOf(float f) {
        return Float.toString(f);
    }

    public static String valueOf(double d) {
        return Double.toString(d);
    }

    public native String intern();

    public String repeat(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count is negative: " + count);
        } else if (count == 1) {
            return this;
        } else {
            int len = this.value.length;
            if (len != 0 && count != 0) {
                if (len == 1) {
                    byte[] single = new byte[count];
                    Arrays.fill(single, this.value[0]);
                    return new String(single, this.coder);
                } else if (2147483647 / count < len) {
                    throw new OutOfMemoryError("Repeating " + len + " bytes String " + count + " times will produce a String exceeding maximum size.");
                } else {
                    int limit = len * count;
                    byte[] multiple = new byte[limit];
                    System.arraycopy(this.value, 0, multiple, 0, len);

                    int copied;
                    for(copied = len; copied < limit - copied; copied <<= 1) {
                        System.arraycopy(multiple, 0, multiple, copied, copied);
                    }

                    System.arraycopy(multiple, 0, multiple, copied, limit - copied);
                    return new String(multiple, this.coder);
                }
            } else {
                return "";
            }
        }
    }

    void getBytes(byte[] dst, int dstBegin, byte coder) {
        if (this.coder() == coder) {
            System.arraycopy(this.value, 0, dst, dstBegin << coder, this.value.length);
        } else {
            StringLatin1.inflate(this.value, 0, dst, dstBegin, this.value.length);
        }

    }

    String(char[] value, int off, int len, Void sig) {
        if (len == 0) {
            this.value = "".value;
            this.coder = "".coder;
        } else {
            if (COMPACT_STRINGS) {
                byte[] val = StringUTF16.compress(value, off, len);
                if (val != null) {
                    this.value = val;
                    this.coder = 0;
                    return;
                }
            }

            this.coder = 1;
            this.value = StringUTF16.toBytes(value, off, len);
        }
    }

    String(AbstractStringBuilder asb, Void sig) {
        byte[] val = asb.getValue();
        int length = asb.length();
        if (asb.isLatin1()) {
            this.coder = 0;
            this.value = Arrays.copyOfRange(val, 0, length);
        } else {
            if (COMPACT_STRINGS) {
                byte[] buf = StringUTF16.compress(val, 0, length);
                if (buf != null) {
                    this.coder = 0;
                    this.value = buf;
                    return;
                }
            }

            this.coder = 1;
            this.value = Arrays.copyOfRange(val, 0, length << 1);
        }

    }

    String(byte[] value, byte coder) {
        this.value = value;
        this.coder = coder;
    }

    byte coder() {
        return COMPACT_STRINGS ? this.coder : 1;
    }

    byte[] value() {
        return this.value;
    }

    private boolean isLatin1() {
        return COMPACT_STRINGS && this.coder == 0;
    }

    static void checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new StringIndexOutOfBoundsException("index " + index + ",length " + length);
        }
    }

    static void checkOffset(int offset, int length) {
        if (offset < 0 || offset > length) {
            throw new StringIndexOutOfBoundsException("offset " + offset + ",length " + length);
        }
    }

    static void checkBoundsOffCount(int offset, int count, int length) {
        if (offset < 0 || count < 0 || offset > length - count) {
            throw new StringIndexOutOfBoundsException("offset " + offset + ", count " + count + ", length " + length);
        }
    }

    static void checkBoundsBeginEnd(int begin, int end, int length) {
        if (begin < 0 || begin > end || end > length) {
            throw new StringIndexOutOfBoundsException("begin " + begin + ", end " + end + ", length " + length);
        }
    }

    static String valueOfCodePoint(int codePoint) {
        if (COMPACT_STRINGS && StringLatin1.canEncode(codePoint)) {
            return new String(StringLatin1.toBytes((char)codePoint), (byte)0);
        } else if (Character.isBmpCodePoint(codePoint)) {
            return new String(StringUTF16.toBytes((char)codePoint), (byte)1);
        } else if (Character.isSupplementaryCodePoint(codePoint)) {
            return new String(StringUTF16.toBytesSupplementary(codePoint), (byte)1);
        } else {
            throw new IllegalArgumentException(format("Not a valid Unicode code point: 0x%X", codePoint));
        }
    }

    private static class CaseInsensitiveComparator implements Comparator<String>, Serializable {
        private static final long serialVersionUID = 8575799808933029326L;

        private CaseInsensitiveComparator() {
        }

        public int compare(String s1, String s2) {
            byte[] v1 = s1.value;
            byte[] v2 = s2.value;
            if (s1.coder() == s2.coder()) {
                return s1.isLatin1() ? StringLatin1.compareToCI(v1, v2) : StringUTF16.compareToCI(v1, v2);
            } else {
                return s1.isLatin1() ? StringLatin1.compareToCI_UTF16(v1, v2) : StringUTF16.compareToCI_Latin1(v1, v2);
            }
        }

        private Object readResolve() {
            return String.CASE_INSENSITIVE_ORDER;
        }
    }
}
