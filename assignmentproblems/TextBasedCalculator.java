package week2.assignmentproblems;

import java.util.*;

public class TextBasedCalculator {

    static boolean isDigit(char c){ int a=c; return a>=48 && a<=57; }
    static boolean isOp(char c){ return c=='+' || c=='-' || c=='*' || c=='/'; }
    static boolean isParen(char c){ return c=='(' || c==')'; }
    static boolean isSpace(char c){ return c==' '; }

    static boolean isValid(String expr){
        if (expr==null || expr.trim().isEmpty()) return false;
        int bal=0;
        for (int i=0;i<expr.length();i++){
            char c=expr.charAt(i);
            int a=c;
            if(!(isDigit(c)||isOp(c)||isParen(c)||isSpace(c))) return false;
            if(c=='(') bal++;
            if(c==')'){ bal--; if(bal<0) return false; }
        }
        if(bal!=0) return false;
        String s=expr.replaceAll("\\s+","");
        if(s.isEmpty()) return false;
        if(s.charAt(0)=='+'||s.charAt(0)=='*'||s.charAt(0)=='/') return false;
        if(isOp(s.charAt(s.length()-1)) || s.charAt(s.length()-1)=='(') return false;
        for(int i=0;i<s.length()-1;i++){
            char c=s.charAt(i), n=s.charAt(i+1);
            if(isOp(c) && isOp(n) && !(n=='-' && (i+2<s.length()) && (isDigit(s.charAt(i+2))||s.charAt(i+2)=='('))) return false;
            if(c=='(' && (isOp(n) && n!='-' && n!='(')) return false;
            if(c=='(' && n==')') return false;
            if(c==')' && (n=='(')) return false;
        }
        return true;
    }

    static int[] toIntArray(List<Integer> list){
        int[] a=new int[list.size()];
        for(int i=0;i<a.length;i++) a[i]=list.get(i);
        return a;
    }
    static char[] toCharArray(List<Character> list){
        char[] a=new char[list.size()];
        for(int i=0;i<a.length;i++) a[i]=list.get(i);
        return a;
    }

    static Object[] parseTokensNoParens(String expr){
        List<Integer> nums=new ArrayList<>();
        List<Character> ops=new ArrayList<>();
        int i=0,n=expr.length();
        while(i<n){
            char c=expr.charAt(i);
            if(isSpace(c)){ i++; continue; }
            if(c=='-' && nums.size()==ops.size()){
                int j=i+1; while(j<n && isSpace(expr.charAt(j))) j++;
                int start=j; while(j<n && isDigit(expr.charAt(j))) j++;
                int val=Integer.parseInt("-"+expr.substring(start,j));
                nums.add(val); i=j; continue;
            }
            if(isDigit(c)){
                int j=i; while(j<n && isDigit(expr.charAt(j))) j++;
                nums.add(Integer.parseInt(expr.substring(i,j)));
                i=j; continue;
            }
            if(isOp(c)){
                ops.add(c); i++; continue;
            }
            i++;
        }
        return new Object[]{toIntArray(nums), toCharArray(ops)};
    }

    static int evalNoParens(String expr, StringBuilder steps){
        Object[] tok=parseTokensNoParens(expr);
        int[] nums=(int[])tok[0];
        char[] ops=(char[])tok[1];
        if(nums.length==0) return 0;
        if(nums.length!=ops.length+1) throw new IllegalArgumentException("Invalid expression");
        List<Integer> nlist=new ArrayList<>();
        List<Character> olist=new ArrayList<>();
        nlist.add(nums[0]);
        for(int i=0;i<ops.length;i++){
            char op=ops[i];
            int a=nlist.get(nlist.size()-1);
            int b=nums[i+1];
            if(op=='*' || op=='/'){
                if(op=='/' && b==0) throw new ArithmeticException("Division by zero");
                int res=(op=='*')?(a*b):(a/b);
                steps.append(a+" "+op+" "+b+" = "+res+"\n");
                nlist.set(nlist.size()-1, res);
            }else{
                nlist.add(b);
                olist.add(op);
            }
        }
        int result=nlist.get(0);
        for(int i=0;i<olist.size();i++){
            char op=olist.get(i);
            int b=nlist.get(i+1);
            int before=result;
            result = (op=='+') ? (result+b) : (result-b);
            steps.append(before+" "+op+" "+b+" = "+result+"\n");
        }
        return result;
    }

    static int evaluate(String expr, StringBuilder steps){
        String s=expr;
        while(true){
            int open=s.lastIndexOf('(');
            if(open==-1) break;
            int close=s.indexOf(')', open);
            if(close==-1) throw new IllegalArgumentException("Unmatched parentheses");
            String inside=s.substring(open+1, close);
            steps.append("("+inside+")\n");
            int val=evalNoParens(inside, steps);
            s = s.substring(0, open) + val + s.substring(close+1);
            steps.append("=> "+s+"\n");
        }
        return evalNoParens(s, steps);
    }

    static void displaySteps(String original, StringBuilder steps, int result){
        System.out.println("Expression: "+original);
        System.out.println("Steps:");
        System.out.print(steps.toString());
        System.out.println("Result: "+result);
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter expression: ");
        String expr=sc.nextLine();
        if(!isValid(expr)){
            System.out.println("Invalid expression");
            return;
        }
        StringBuilder steps=new StringBuilder();
        try{
            int ans=evaluate(expr, steps);
            displaySteps(expr, steps, ans);
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}

