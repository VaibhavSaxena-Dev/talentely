import java.util.*;

// Main class for the Comprehensive Number Checker 
public class ComprehensiveNumberChecker {
    public static void main(String[] args) {
        int[] numbers = {1, 4, 11, 16, 21, 121, 153, 6, 28, 9474, 1634, 703, 145, 123, 19, 44};

        for (int num : numbers) {
            System.out.println("Number: " + num);
            System.out.println("Even: " + isEven(num));
            System.out.println("Odd: " + isOdd(num));
            System.out.println("Prime (Math): " + isPrimeMath(num));
            System.out.println("Prime (Bit Manip): " + isPrimeBitManip(num));
            System.out.println("Perfect Number: " + isPerfectNumber(num));
            System.out.println("Deficient Number: " + isDeficientNumber(num));
            System.out.println("Abundant Number: " + isAbundantNumber(num));
            System.out.println("Perfect Square (Math): " + isPerfectSquareMath(num));
            System.out.println("Perfect Square (Bit Manip): " + isPerfectSquareBitManip(num));
            System.out.println("Fibonacci: " + isFibonacci(num));
            System.out.println("Armstrong Number: " + isArmstrong(num));
            System.out.println("Palindrome (Math): " + isPalindromeMath(num));
            System.out.println("Harshad Number: " + isHarshadNumber(num));
            System.out.println("Automorphic Number: " + isAutomorphic(num));
            System.out.println("Kaprekar Number: " + isKaprekar(num));
            System.out.println("Spy Number: " + isSpyNumber(num));
            System.out.println("Duck Number: " + isDuckNumber(num));
            System.out.println("Happy Number: " + isHappyNumber(num));
            System.out.println("Sad Number: " + !isHappyNumber(num));  // Sad if not Happy
            System.out.println("Special Number: " + isSpecialNumber(num));
            System.out.println("Keith Number: " + isKeithNumber(num));
            System.out.println("Circular Prime: " + isCircularPrime(num));
            System.out.println("Smith Number: " + isSmithNumber(num));
            System.out.println("=============================================");
        }
    }

    public static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    public static boolean isOdd(int n) {
        return (n & 1) == 1;
    }

    public static boolean isPrimeMath(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean isPrimeBitManip(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if ((n & 1) == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean isPerfectNumber(int n) {
        if (n <= 0) return false;
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i;
                if (i != n / i) sum += n / i;
            }
        }
        return sum == n && n != 1;
    }

    public static boolean isDeficientNumber(int n) {
        if (n <= 0) return false;
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i;
                if (i != n / i) sum += n / i;
            }
        }
        return sum < n;
    }

    public static boolean isAbundantNumber(int n) {
        if (n <= 0) return false;
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i;
                if (i != n / i) sum += n / i;
            }
        }
        return sum > n;
    }

    public static boolean isPerfectSquareMath(int n) {
        if (n < 0) return false;
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }

    public static boolean isPerfectSquareBitManip(int n) {
        if (n < 0) return false;
        int x = n;
        while ((x * x) > n) {
            x = (x + n / x) / 2;
        }
        return x * x == n;
    }

    public static boolean isFibonacci(int n) {
        if (n < 0) return false;
        return isPerfectSquareMath(5 * n * n + 4) || isPerfectSquareMath(5 * n * n - 4);
    }

    public static boolean isArmstrong(int n) {
        int sum = 0, temp = n, digits = String.valueOf(n).length();
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }
        return sum == n;
    }

    public static boolean isPalindromeMath(int n) {
        int original = n, reversed = 0;
        while (n > 0) {
            reversed = (reversed * 10) + (n % 10);
            n /= 10;
        }
        return original == reversed;
    }

    public static boolean isHarshadNumber(int n) {
        if (n <= 0) return false;
        int sum = 0, temp = n;
        while (temp > 0) {
            sum += temp % 10;
            temp /= 10;
        }
        return n % sum == 0;
    }

    public static boolean isAutomorphic(int n) {
        int square = n * n;
        return Integer.toString(square).endsWith(Integer.toString(n));
    }

    public static boolean isKaprekar(int n) {
        if (n == 1) return true;
        int square = n * n;
        String str = String.valueOf(square);
        int len = str.length();
        String left = str.substring(0, len / 2);
        String right = str.substring(len / 2);
        int l = (left.isEmpty()) ? 0 : Integer.parseInt(left);
        int r = Integer.parseInt(right);
        return l + r == n;
    }

    public static boolean isSpyNumber(int n) {
        int sum = 0, prod = 1;
        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            prod *= digit;
            n /= 10;
        }
        return sum == prod;
    }

    public static boolean isDuckNumber(int n) {
        String str = Integer.toString(n);
        return str.contains("0") && str.charAt(0) != '0';
    }

    public static boolean isHappyNumber(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            n = sum;
        }
        return n == 1;
    }

    public static boolean isSpecialNumber(int n) {
        int sum = 0, temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            sum += factorial(digit);
            temp /= 10;
        }
        return sum == n;
    }

    public static int factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) fact *= i;
        return fact;
    }

    public static boolean isKeithNumber(int n) {
        List<Integer> terms = new ArrayList<>();
        int temp = n;
        while (temp > 0) {
            terms.add(0, temp % 10);
            temp /= 10;
        }
        int sum = 0, i = terms.size();
        while (sum < n) {
            sum = 0;
            for (int j = terms.size() - i; j < terms.size(); j++) {
                sum += terms.get(j);
            }
            terms.add(sum);
        }
        return sum == n;
    }

    public static boolean isCircularPrime(int n) {
        if (!isPrimeMath(n)) return false;
        String str = Integer.toString(n);
        for (int i = 1; i < str.length(); i++) {
            String rotated = str.substring(i) + str.substring(0, i);
            if (!isPrimeMath(Integer.parseInt(rotated))) return false;
        }
        return true;
    }

    public static boolean isSmithNumber(int n) {
        if (isPrimeMath(n) || n < 2) return false;

        int sumDigits = sumOfDigits(n);
        int sumFactors = 0;
        int num = n;

        for (int i = 2; i <= num / i; i++) {
            while (n % i == 0) {
                sumFactors += sumOfDigits(i);
                n /= i;
            }
        }

        if (n > 1) sumFactors += sumOfDigits(n);

        return sumDigits == sumFactors;
    }

    public static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
