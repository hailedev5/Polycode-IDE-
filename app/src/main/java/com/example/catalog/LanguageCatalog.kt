package com.example.catalog

import com.example.model.ExecutionMode
import com.example.model.LanguageCategory
import com.example.model.LanguageDefinition

object LanguageCatalog {

    private fun c(str: String): String = str.replace("§", "$")

    val languages: List<LanguageDefinition> = listOf(
        // ================= POPULAR =================
        LanguageDefinition(
            id = "python",
            name = "Python",
            extension = "py",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1991,
            description = "High-level, dynamically typed language prized for readability, data science, and scripting.",
            helloWorldCode = c("""# Python 3
def greet(name: str) -> str:
    return f"Hello, {name}! Welcome to PolyCode."

message = greet("Developer")
print(message)
"""),
            algorithmCode = c("""# QuickSort Algorithm
def quicksort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quicksort(left) + middle + quicksort(right)

numbers = [64, 34, 25, 12, 22, 11, 90]
print("Unsorted:", numbers)
print("Sorted:", quicksort(numbers))
"""),
            keywords = listOf("def", "class", "if", "elif", "else", "for", "while", "return", "import", "from", "as", "try", "except", "with", "lambda", "yield", "async", "await"),
            cheatSheet = "• Data types: int, float, str, list, dict, set, tuple\n• Comprehensions: [x*2 for x in items if x > 0]\n• Lambdas: square = lambda x: x ** 2\n• Context: with open('file.txt') as f:\n• F-strings: f'Value: {x:.2f}'",
            paradigms = listOf("Multi-paradigm", "Object-Oriented", "Functional")
        ),
        LanguageDefinition(
            id = "javascript",
            name = "JavaScript",
            extension = "js",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.JAVASCRIPT_NATIVE,
            year = 1995,
            description = "The universal language of the web, running on modern JS V8 engines.",
            helloWorldCode = c("""// Modern ES6+ JavaScript
function greet(user) {
    const timestamp = new Date().toLocaleTimeString();
    return `[§{timestamp}] Hello, §{user}! Ready to code?`;
}

console.log(greet("PolyCode Explorer"));
const primes = [2, 3, 5, 7, 11].map(n => n * n);
console.log("Squared primes:", primes);
"""),
            algorithmCode = c("""// Fibonacci sequence generator
function fibonacci(n) {
    const seq = [0, 1];
    for (let i = 2; i < n; i++) {
        seq.push(seq[i - 1] + seq[i - 2]);
    }
    return seq;
}

const terms = 10;
console.log(`First §{terms} Fibonacci numbers:`, fibonacci(terms));
"""),
            keywords = listOf("const", "let", "var", "function", "class", "if", "else", "return", "async", "await", "import", "export", "try", "catch", "new", "this"),
            cheatSheet = c("• Arrow functions: (a, b) => a + b\n• Destructuring: const { id, name } = user\n• Spread: [...arr1, ...arr2]\n• Promises: fetch(url).then(r => r.json())\n• Template literals: `Total: §{sum}`"),
            paradigms = listOf("Event-driven", "Functional", "Prototype-based")
        ),
        LanguageDefinition(
            id = "typescript",
            name = "TypeScript",
            extension = "ts",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.JAVASCRIPT_NATIVE,
            year = 2012,
            description = "Strict syntactical superset of JavaScript adding static typing and interfaces.",
            helloWorldCode = c("""interface Developer {
    name: string;
    skills: string[];
    isOffline: boolean;
}

function introduce(dev: Developer): string {
    return `§{dev.name} excels in §{dev.skills.join(", ")} (Offline Mode: §{dev.isOffline})`;
}

const dev: Developer = {
    name: "Alex",
    skills: ["TypeScript", "Kotlin", "Rust"],
    isOffline: true
};

console.log(introduce(dev));
"""),
            algorithmCode = c("""class BinarySearchTree<T> {
    constructor(public value: T, public left?: BinarySearchTree<T>, public right?: BinarySearchTree<T>) {}

    insert(val: T): void {
        if (val < this.value) {
            this.left ? this.left.insert(val) : this.left = new BinarySearchTree(val);
        } else {
            this.right ? this.right.insert(val) : this.right = new BinarySearchTree(val);
        }
    }
}

const bst = new BinarySearchTree(50);
[30, 70, 20, 40, 60, 80].forEach(n => bst.insert(n));
console.log("BST root constructed with value:", bst.value);
"""),
            keywords = listOf("interface", "type", "enum", "namespace", "implements", "readonly", "as", "keyof", "typeof"),
            cheatSheet = "• Generics: function identity<T>(arg: T): T\n• Unions: type Status = 'idle' | 'loading' | 'success'\n• Utility types: Partial<T>, Record<K, V>, Pick<T, K>",
            paradigms = listOf("Static Typed", "Object-Oriented", "Functional")
        ),
        LanguageDefinition(
            id = "c",
            name = "C",
            extension = "c",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1972,
            description = "The foundational procedural language powering OS kernels, embedded systems, and compilers.",
            helloWorldCode = c("""#include <stdio.h>

int main(void) {
    printf("Hello from ANSI C!\n");
    int answer = 42;
    printf("The Answer to the Ultimate Question is: %d\n", answer);
    return 0;
}
"""),
            algorithmCode = c("""#include <stdio.h>

long factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}

int main(void) {
    int num = 7;
    printf("Factorial of %d is %ld\n", num, factorial(num));
    return 0;
}
"""),
            keywords = listOf("int", "char", "float", "double", "void", "struct", "union", "typedef", "sizeof", "static", "const", "volatile", "if", "else", "for", "while"),
            cheatSheet = "• Pointers: int *ptr = &val; *ptr = 10;\n• Memory: malloc(size), free(ptr)\n• Struct: struct Point { int x, y; };\n• Arrays: int arr[10];",
            paradigms = listOf("Procedural", "Imperative", "Low-level")
        ),
        LanguageDefinition(
            id = "cpp",
            name = "C++",
            extension = "cpp",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1985,
            description = "High-performance systems language with zero-cost abstractions and STL.",
            helloWorldCode = c("""#include <iostream>
#include <vector>
#include <string>

int main() {
    std::string greeting = "Hello, Modern C++20!";
    std::cout << greeting << std::endl;
    
    std::vector<int> nums = {1, 2, 3, 4, 5};
    for (const auto& n : nums) {
        std::cout << "Element: " << n << std::endl;
    }
    return 0;
}
"""),
            algorithmCode = c("""#include <iostream>
#include <vector>
#include <algorithm>

int main() {
    std::vector<int> v = {42, 13, 89, 7, 25};
    std::sort(v.begin(), v.end());
    std::cout << "Sorted C++ STL vector: ";
    for (int n : v) std::cout << n << " ";
    std::cout << std::endl;
    return 0;
}
"""),
            keywords = listOf("class", "template", "typename", "public", "private", "protected", "virtual", "override", "namespace", "auto", "nullptr", "constexpr"),
            cheatSheet = "• Smart pointers: std::unique_ptr, std::shared_ptr\n• Lambdas: auto fn = [](int a, int b) { return a + b; };\n• STL: vector, map, unordered_map, set\n• RAII handles resource cleanup automatically.",
            paradigms = listOf("Multi-paradigm", "Object-Oriented", "Generic")
        ),
        LanguageDefinition(
            id = "java",
            name = "Java",
            extension = "java",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1995,
            description = "Enterprise class-based, object-oriented language for cross-platform JVM runtimes.",
            helloWorldCode = c("""public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Java Enterprise Edition!");
        var items = java.util.List.of("Android", "JVM", "Spring", "Kotlin");
        items.forEach(item -> System.out.println("Tech: " + item));
    }
}
"""),
            algorithmCode = c("""import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        Arrays.sort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
"""),
            keywords = listOf("public", "class", "static", "void", "final", "abstract", "interface", "extends", "implements", "package", "import", "throws"),
            cheatSheet = "• Streams: list.stream().filter(x -> x > 0).toList()\n• Records: record User(String name, int age) {}\n• Optionals: Optional.ofNullable(val).orElse(\"default\")",
            paradigms = listOf("Object-Oriented", "Structured", "Generic")
        ),
        LanguageDefinition(
            id = "kotlin",
            name = "Kotlin",
            extension = "kt",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2011,
            description = "Modern, concise, null-safe language by JetBrains, the official language for Android.",
            helloWorldCode = c("""fun main() {
    val languages = listOf("Kotlin", "Rust", "TypeScript", "Python")
    println("Welcome to PolyCode IDE powered by Kotlin!")
    languages.forEachIndexed { idx, lang ->
        println("§{idx + 1}. §lang (Null-safe & Expressive)")
    }
}
"""),
            algorithmCode = c("""// Sieve of Eratosthenes
fun findPrimes(limit: Int): List<Int> {
    val isPrime = BooleanArray(limit + 1) { true }
    isPrime[0] = false
    isPrime[1] = false
    for (p in 2..Math.sqrt(limit.toDouble()).toInt()) {
        if (isPrime[p]) {
            for (i in p * p..limit step p) isPrime[i] = false
        }
    }
    return (2..limit).filter { isPrime[it] }
}

fun main() {
    val primes = findPrimes(30)
    println("Primes up to 30: §primes")
}
"""),
            keywords = listOf("val", "var", "fun", "class", "data", "sealed", "object", "when", "is", "in", "by", "companion", "suspend", "inline"),
            cheatSheet = "• Null-safety: val len = str?.length ?: 0\n• Data classes: data class User(val id: Int, val name: String)\n• When expressions: when (x) { 1 -> \"one\"; else -> \"other\" }\n• Coroutines: suspend fun fetchData()",
            paradigms = listOf("Multi-paradigm", "Functional", "Object-Oriented")
        ),
        LanguageDefinition(
            id = "rust",
            name = "Rust",
            extension = "rs",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2010,
            description = "Memory-safe systems programming language with zero-cost abstractions and no garbage collector.",
            helloWorldCode = c("""fn main() {
    let app = "PolyCode";
    println!("Hello from Rust! Building ultra-reliable systems in {}", app);
    let numbers: Vec<i32> = (1..=5).map(|x| x * 10).collect();
    println!("Vector: {:?}", numbers);
}
"""),
            algorithmCode = c("""fn is_palindrome(s: &str) -> bool {
    let clean: String = s.chars().filter(|c| c.is_alphanumeric()).map(|c| c.to_ascii_lowercase()).collect();
    clean == clean.chars().rev().collect::<String>()
}

fn main() {
    let test = "A man, a plan, a canal: Panama";
    println!("'{}' is palindrome? {}", test, is_palindrome(test));
}
"""),
            keywords = listOf("fn", "let", "mut", "struct", "enum", "impl", "trait", "match", "pub", "mod", "use", "unsafe", "where", "async", "await"),
            cheatSheet = "• Ownership & Borrowing: &T (immutable ref), &mut T (mutable ref)\n• Pattern matching: match result { Ok(v) => v, Err(e) => panic!() }\n• Traits: impl Summary for NewsArticle {}\n• Enums with values: Option<T>, Result<T, E>",
            paradigms = listOf("Systems", "Functional", "Concurrent")
        ),
        LanguageDefinition(
            id = "go",
            name = "Go (Golang)",
            extension = "go",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2009,
            description = "Fast, statically typed, compiled language engineered by Google for concurrent cloud microservices.",
            helloWorldCode = c("""package main

import "fmt"

func main() {
    message := "Hello from Go! Simplicity is prerequisite for reliability."
    fmt.Println(message)
    ch := make(chan string)
    go func() { ch <- "Goroutine communication complete!" }()
    fmt.Println(<-ch)
}
"""),
            algorithmCode = c("""package main

import "fmt"

func binarySearch(arr []int, target int) int {
    low, high := 0, len(arr)-1
    for low <= high {
        mid := (low + high) / 2
        if arr[mid] == target {
            return mid
        } else if arr[mid] < target {
            low = mid + 1
        } else {
            high = mid - 1
        }
    }
    return -1
}

func main() {
    nums := []int{2, 5, 8, 12, 16, 23, 38, 56, 72, 91}
    idx := binarySearch(nums, 23)
    fmt.Printf("Element 23 found at index: %d\n", idx)
}
"""),
            keywords = listOf("package", "import", "func", "var", "const", "type", "struct", "interface", "chan", "go", "select", "defer", "range"),
            cheatSheet = "• Goroutines: go doWork()\n• Channels: c <- data, val := <-c\n• Error handling: if err != nil { return err }\n• Slices: s := make([]int, 0, 10)\n• Defer: defer f.Close()",
            paradigms = listOf("Concurrent", "Imperative", "Structural")
        ),
        LanguageDefinition(
            id = "csharp",
            name = "C#",
            extension = "cs",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2000,
            description = "Microsoft's flagship modern, component-oriented language for .NET and Unity.",
            helloWorldCode = c("""using System;
using System.Linq;

class Program {
    static void Main() {
        Console.WriteLine("Hello from C# 12 and .NET!");
        var numbers = new[] { 1, 2, 3, 4, 5, 6 };
        var evens = numbers.Where(n => n % 2 == 0);
        Console.WriteLine("Even numbers: " + string.Join(", ", evens));
    }
}
"""),
            algorithmCode = c("""using System;

class Program {
    static int GCD(int a, int b) => b == 0 ? a : GCD(b, a % b);

    static void Main() {
        Console.WriteLine($"GCD of 48 and 18: {GCD(48, 18)}");
    }
}
"""),
            keywords = listOf("class", "struct", "record", "interface", "namespace", "using", "async", "await", "public", "private", "var", "get", "set"),
            cheatSheet = "• LINQ: from x in list where x > 5 select x\n• Records: record Person(string Name, int Age);\n• Null-coalescing: name ?? \"Anonymous\"\n• Pattern matching: x switch { int i => i * 2, _ => 0 }",
            paradigms = listOf("Object-Oriented", "Functional", "Component-oriented")
        ),
        LanguageDefinition(
            id = "swift",
            name = "Swift",
            extension = "swift",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2014,
            description = "Apple's fast, modern, safe language for iOS, macOS, watchOS, and server-side.",
            helloWorldCode = c("""import Foundation

let platform = "Apple Platforms & Beyond"
print("Hello from Swift! Building apps for \(platform).")

let scores = [85, 92, 78, 99, 88]
let honorRoll = scores.filter { §0 >= 90 }
print("Honor roll scores: \(honorRoll)")
"""),
            algorithmCode = c("""func reverseString(_ s: String) -> String {
    return String(s.reversed())
}

let original = "PolyCode IDE"
print("Reversed: \(reverseString(original))")
"""),
            keywords = listOf("let", "var", "func", "class", "struct", "enum", "protocol", "extension", "guard", "if", "switch", "defer", "actor"),
            cheatSheet = "• Optionals: guard let value = opt else { return }\n• Closures: { (a: Int) -> Int in a * 2 }\n• Extensions: extension String { var isTrimmed: Bool { ... } }\n• Protocols: protocol Describable { var desc: String { get } }",
            paradigms = listOf("Protocol-Oriented", "Functional", "Object-Oriented")
        ),
        LanguageDefinition(
            id = "ruby",
            name = "Ruby",
            extension = "rb",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1995,
            description = "Dynamic, open-source language focused on developer happiness, simplicity, and Rails.",
            helloWorldCode = c("""# Ruby
class Greeter
  def initialize(name)
    @name = name
  end

  def say_hi
    puts "Hello, #{@name}! Everything in Ruby is an object."
  end
end

g = Greeter.new("Coder")
g.say_hi
3.times { |i| puts "Iteration #{i + 1}" }
"""),
            algorithmCode = c("""# Palindrome check in Ruby
def palindrome?(str)
  clean = str.downcase.gsub(/[^a-z0-9]/, '')
  clean == clean.reverse
end

puts "racecar: #{palindrome?('racecar')}"
puts "hello: #{palindrome?('hello')}"
"""),
            keywords = listOf("def", "end", "class", "module", "if", "unless", "elsif", "else", "case", "when", "yield", "block", "attr_accessor"),
            cheatSheet = "• Blocks: [1, 2, 3].map { |x| x * 2 }\n• Symbols: :name, :status\n• String interpolation: \"Value: #{x}\"\n• Safe navigation: user&.profile&.avatar",
            paradigms = listOf("Object-Oriented", "Reflective", "Functional")
        ),
        LanguageDefinition(
            id = "php",
            name = "PHP",
            extension = "php",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1995,
            description = "Popular general-purpose scripting language especially suited to web backend development.",
            helloWorldCode = c("""<?php
declare(strict_types=1);

function greet(string §name): string {
    return "Hello, §name! PHP powers over 75% of the web.";
}

echo greet("Modern Developer") . PHP_EOL;

§frameworks = ["Laravel", "Symfony", "WordPress"];
foreach (§frameworks as §fw) {
    echo "• §fw" . PHP_EOL;
}
?>
"""),
            algorithmCode = c("""<?php
function bubbleSort(array §arr): array {
    §n = count(§arr);
    for (§i = 0; §i < §n; §i++) {
        for (§j = 0; §j < §n - §i - 1; §j++) {
            if (§arr[§j] > §arr[§j + 1]) {
                §temp = §arr[§j];
                §arr[§j] = §arr[§j + 1];
                §arr[§j + 1] = §temp;
            }
        }
    }
    return §arr;
}

§nums = [64, 34, 25, 12, 22, 11, 90];
print_r(bubbleSort(§nums));
?>
"""),
            keywords = listOf("function", "class", "echo", "return", "public", "private", "protected", "namespace", "use", "match", "readonly"),
            cheatSheet = c("• Associative arrays: ['key' => 'value']\n• Null coalescing: §val = §opt ?? 'default'\n• Arrow functions: fn(§x) => §x * 2\n• Match expression: match(§code) { 200 => 'OK', default => 'Err' }"),
            paradigms = listOf("Imperative", "Object-Oriented", "Functional")
        ),

        // ================= WEB TECHNOLOGIES =================
        LanguageDefinition(
            id = "html5",
            name = "HTML5",
            extension = "html",
            category = LanguageCategory.WEB,
            executionMode = ExecutionMode.WEB_VIEW_RENDER,
            year = 1993,
            description = "The standard markup language for documents designed to be displayed in a web browser.",
            helloWorldCode = c("""<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <style>
    body { font-family: system-ui, sans-serif; background: #0f172a; color: #f8fafc; padding: 24px; text-align: center; }
    .card { background: #1e293b; border-radius: 12px; padding: 20px; box-shadow: 0 4px 20px rgba(0,0,0,0.5); display: inline-block; }
    h1 { color: #38bdf8; margin-bottom: 8px; }
    button { background: #38bdf8; color: #0f172a; border: none; padding: 10px 20px; font-weight: bold; border-radius: 8px; cursor: pointer; }
  </style>
</head>
<body>
  <div class="card">
    <h1>🚀 PolyCode Web Sandbox</h1>
    <p>Live offline rendering of HTML5, CSS3 & JavaScript!</p>
    <button onclick="alert('Hello from Offline Web Engine!')">Click Me</button>
  </div>
</body>
</html>
"""),
            algorithmCode = c("""<!DOCTYPE html>
<html>
<head>
  <style>
    body { background: #111; color: #eee; font-family: monospace; display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100vh; margin: 0; }
    .progress-bar { width: 80%; background: #333; border-radius: 8px; overflow: hidden; height: 24px; margin-top: 16px; }
    .progress-fill { height: 100%; width: 75%; background: linear-gradient(90deg, #00d2ff, #3a7bd5); transition: width 0.3s; }
  </style>
</head>
<body>
  <h2>Web Component Progress Demo</h2>
  <div class="progress-bar"><div class="progress-fill"></div></div>
</body>
</html>
"""),
            keywords = listOf("html", "head", "body", "div", "span", "p", "a", "button", "input", "canvas", "svg", "script", "style"),
            cheatSheet = "• Semantic tags: <header>, <main>, <nav>, <section>, <article>, <footer>\n• Forms: <form>, <input type='text'>, <select>, <textarea>\n• Media: <img src='...'>, <video controls>, <audio controls>",
            paradigms = listOf("Markup", "Declarative")
        ),
        LanguageDefinition(
            id = "css3",
            name = "CSS3",
            extension = "css",
            category = LanguageCategory.WEB,
            executionMode = ExecutionMode.WEB_VIEW_RENDER,
            year = 1996,
            description = "Style sheet language used for describing the presentation of a document written in HTML or XML.",
            helloWorldCode = c("""<!DOCTYPE html>
<html>
<head>
<style>
/* Modern CSS3 Flexbox & Gradients */
body {
  margin: 0;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: radial-gradient(circle at center, #1a1c29, #0d0e15);
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
.neon-box {
  padding: 30px 50px;
  background: rgba(255, 255, 255, 0.05);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(0, 210, 255, 0.4);
  border-radius: 16px;
  color: #00d2ff;
  font-size: 24px;
  text-shadow: 0 0 10px rgba(0, 210, 255, 0.8);
  box-shadow: 0 0 25px rgba(0, 210, 255, 0.3);
}
</style>
</head>
<body>
  <div class="neon-box">⚡ Cyber CSS3 Active</div>
</body>
</html>
"""),
            algorithmCode = c("""<!DOCTYPE html>
<html>
<head>
<style>
.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12px;
  padding: 20px;
  background: #181824;
  color: white;
  font-family: sans-serif;
}
.grid-item {
  background: #282a36;
  padding: 20px;
  text-align: center;
  border-radius: 8px;
  border: 1px solid #44475a;
}
</style>
</head>
<body>
  <div class="grid-container">
    <div class="grid-item">Grid 1</div>
    <div class="grid-item">Grid 2</div>
    <div class="grid-item">Grid 3</div>
    <div class="grid-item">Grid 4</div>
    <div class="grid-item">Grid 5</div>
    <div class="grid-item">Grid 6</div>
  </div>
</body>
</html>
"""),
            keywords = listOf("display", "flex", "grid", "color", "background", "margin", "padding", "border", "animation", "transform", "transition"),
            cheatSheet = "• Flexbox: display: flex; justify-content: center; align-items: center;\n• Grid: display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));\n• Transitions: transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);",
            paradigms = listOf("Styling", "Declarative")
        ),
        LanguageDefinition(
            id = "canvas2d",
            name = "Canvas 2D",
            extension = "html",
            category = LanguageCategory.WEB,
            executionMode = ExecutionMode.WEB_VIEW_RENDER,
            year = 2004,
            description = "High-performance immediate-mode 2D graphics API in HTML5.",
            helloWorldCode = c("""<!DOCTYPE html>
<html>
<body style="margin:0; background:#000; overflow:hidden;">
  <canvas id="c" width="400" height="300"></canvas>
  <script>
    const canvas = document.getElementById('c');
    const ctx = canvas.getContext('2d');
    
    let angle = 0;
    function render() {
      ctx.fillStyle = 'rgba(0, 0, 0, 0.1)';
      ctx.fillRect(0, 0, canvas.width, canvas.height);
      
      const cx = canvas.width / 2;
      const cy = canvas.height / 2;
      
      for(let i = 0; i < 8; i++) {
        const rad = angle + (i * Math.PI / 4);
        const x = cx + Math.cos(rad) * 80;
        const y = cy + Math.sin(rad) * 80;
        ctx.beginPath();
        ctx.arc(x, y, 14, 0, Math.PI * 2);
        ctx.fillStyle = `hsl(§{(i * 45 + angle * 50) % 360}, 80%, 60%)`;
        ctx.fill();
      }
      angle += 0.03;
      requestAnimationFrame(render);
    }
    render();
  </script>
</body>
</html>
"""),
            algorithmCode = c("""<!DOCTYPE html>
<html>
<body style="margin:0; background:#0b0f19;">
<canvas id="cv" width="360" height="260"></canvas>
<script>
const cv = document.getElementById('cv');
const ctx = cv.getContext('2d');
for (let px = 0; px < 360; px += 2) {
  for (let py = 0; py < 260; py += 2) {
    let x0 = (px - 240) / 100;
    let y0 = (py - 130) / 100;
    let x = 0, y = 0, iter = 0;
    while (x*x + y*y <= 4 && iter < 30) {
      let xtemp = x*x - y*y + x0;
      y = 2*x*y + y0;
      x = xtemp;
      iter++;
    }
    ctx.fillStyle = "hsl(" + (iter * 12) + ", 100%, " + (iter < 30 ? 50 : 0) + "%)";
    ctx.fillRect(px, py, 2, 2);
  }
}
</script>
</body>
</html>
"""),
            keywords = listOf("getContext", "fillRect", "beginPath", "arc", "stroke", "fill", "requestAnimationFrame", "drawImage"),
            cheatSheet = "• Context: const ctx = canvas.getContext('2d');\n• Rects: ctx.fillRect(x, y, w, h);\n• Paths: ctx.beginPath(); ctx.moveTo(x1, y1); ctx.lineTo(x2, y2); ctx.stroke();",
            paradigms = listOf("Graphics", "Imperative")
        ),
        LanguageDefinition(
            id = "svg",
            name = "SVG",
            extension = "svg",
            category = LanguageCategory.WEB,
            executionMode = ExecutionMode.WEB_VIEW_RENDER,
            year = 2001,
            description = "Scalable Vector Graphics XML-based vector image format.",
            helloWorldCode = c("""<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 300 300" width="100%" height="100%" style="background:#0a0e17">
  <defs>
    <linearGradient id="grad" x1="0%" y1="0%" x2="100%" y2="100%">
      <stop offset="0%" stop-color="#00f2fe" />
      <stop offset="100%" stop-color="#4facfe" />
    </linearGradient>
    <filter id="glow">
      <feGaussianBlur stdDeviation="4" result="coloredBlur"/>
      <feMerge>
        <feMergeNode in="coloredBlur"/>
        <feMergeNode in="SourceGraphic"/>
      </feMerge>
    </filter>
  </defs>
  <circle cx="150" cy="150" r="80" fill="url(#grad)" filter="url(#glow)" />
  <polygon points="120,110 200,150 120,190" fill="#ffffff" />
  <text x="150" y="270" text-anchor="middle" fill="#00f2fe" font-family="sans-serif" font-size="16">Vector Graphic Engine</text>
</svg>
"""),
            algorithmCode = c("""<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200" style="background:#151520">
  <path d="M 20 100 Q 100 20 180 100 T 20 100" fill="none" stroke="#ff007f" stroke-width="4"/>
  <circle cx="100" cy="100" r="15" fill="#00ffcc" />
</svg>
"""),
            keywords = listOf("svg", "rect", "circle", "ellipse", "line", "polyline", "polygon", "path", "text", "defs", "g", "filter"),
            cheatSheet = "• Path commands: M (moveto), L (lineto), H/V, C/S (cubic bezier), Q/T (quadratic), A (arc), Z (closepath)",
            paradigms = listOf("Vector", "Declarative")
        ),
        LanguageDefinition(
            id = "sql",
            name = "SQL (SQLite)",
            extension = "sql",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.SQL_NATIVE,
            year = 1974,
            description = "Structured Query Language executed directly on Android's built-in native SQLite engine.",
            helloWorldCode = c("""-- Real Offline SQLite Execution
CREATE TABLE developers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    favorite_language TEXT NOT NULL,
    years_experience INTEGER DEFAULT 1
);

INSERT INTO developers (name, favorite_language, years_experience) VALUES 
('Ada Lovelace', 'C', 10),
('Grace Hopper', 'COBOL', 15),
('Dennis Ritchie', 'C', 12),
('Linus Torvalds', 'Rust', 8),
('Guido van Rossum', 'Python', 9);

SELECT id, name, favorite_language, years_experience 
FROM developers 
ORDER BY years_experience DESC;
"""),
            algorithmCode = c("""-- Aggregation and grouping
CREATE TABLE sales (product TEXT, category TEXT, revenue REAL);
INSERT INTO sales VALUES 
('Laptop', 'Tech', 1200.0),
('Phone', 'Tech', 800.0),
('Chair', 'Office', 150.0),
('Desk', 'Office', 300.0),
('Monitor', 'Tech', 250.0);

SELECT category, COUNT(*) as items_sold, SUM(revenue) as total_revenue, AVG(revenue) as avg_price
FROM sales
GROUP BY category
ORDER BY total_revenue DESC;
"""),
            keywords = listOf("SELECT", "FROM", "WHERE", "GROUP BY", "ORDER BY", "JOIN", "LEFT JOIN", "INSERT INTO", "CREATE TABLE", "UPDATE", "DELETE"),
            cheatSheet = "• Joins: SELECT * FROM A JOIN B ON A.id = B.a_id\n• Aggregate functions: COUNT(), SUM(), AVG(), MIN(), MAX()\n• Constraints: NOT NULL, UNIQUE, PRIMARY KEY, FOREIGN KEY",
            paradigms = listOf("Declarative", "Relational")
        ),

        // ================= ESOTERIC =================
        LanguageDefinition(
            id = "brainfuck",
            name = "Brainfuck",
            extension = "bf",
            category = LanguageCategory.ESOTERIC,
            executionMode = ExecutionMode.BRAINFUCK_NATIVE,
            year = 1993,
            description = "Minimalist Turing-complete esoteric language using 8 single-character instructions on a byte tape.",
            helloWorldCode = c("""[ Prints Hello World! ]
++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.
"""),
            algorithmCode = c("""[ Addition: adds cell 0 to cell 1 ]
++       Cell 0 = 2
> +++    Cell 1 = 3
< [ -> + < ]  Loop: decrement cell 0, increment cell 1
> .      Output result (ASCII 5 = non-printable, tape check)
"""),
            keywords = listOf(">", "<", "+", "-", ".", ",", "[", "]"),
            cheatSheet = "• > increment pointer\n• < decrement pointer\n• + increment byte\n• - decrement byte\n• . output byte\n• , read byte\n• [ jump forward if 0\n• ] jump back if not 0",
            paradigms = listOf("Esoteric", "Turing-complete")
        ),
        LanguageDefinition(
            id = "lisp",
            name = "Common Lisp",
            extension = "lisp",
            category = LanguageCategory.FUNCTIONAL,
            executionMode = ExecutionMode.LISP_NATIVE,
            year = 1958,
            description = "The classic symbolic computing language with nested S-expressions and homoiconicity.",
            helloWorldCode = c(""";; Common Lisp S-expression
(define greeting "Hello from Lisp! Code is data.")
(print greeting)

(define (square x) (* x x))
(print (square 9))
"""),
            algorithmCode = c(""";; Recursive Factorial
(define (fact n)
  (if (<= n 1)
      1
      (* n (fact (- n 1)))))

(print (fact 6))
"""),
            keywords = listOf("define", "lambda", "if", "cond", "let", "car", "cdr", "cons", "print"),
            cheatSheet = "• S-expr: (func arg1 arg2)\n• Lambda: (lambda (x) (* x 2))\n• List head: (car list)\n• List tail: (cdr list)\n• Construct: (cons head tail)",
            paradigms = listOf("Functional", "Homoiconic", "Metaprogramming")
        ),
        LanguageDefinition(
            id = "regex",
            name = "Regular Expressions",
            extension = "regex",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.REGEX_NATIVE,
            year = 1951,
            description = "Pattern matching and extraction engine for lexical analysis, validation, and parsing.",
            helloWorldCode = c("""Pattern: (\\w+)@([\\w\\.-]+)\\.([a-z\\.]{2,6})
Test Text: Contact us at support@polycode.io or admin@developer.org for offline help!
"""),
            algorithmCode = c("""Pattern: (\\d{4})-(\\d{2})-(\\d{2})
Test Text: Logs recorded on 2026-10-30 and completed on 2026-11-01.
"""),
            keywords = listOf("^", "$", ".", "*", "+", "?", "(", ")", "[", "]", "{", "}", "|", "\\d", "\\w", "\\s"),
            cheatSheet = "• \\d = digits, \\w = word char, \\s = whitespace\n• ^ = line start, $ = line end\n• + = 1 or more, * = 0 or more, ? = optional\n• (?<name>...) = named group",
            paradigms = listOf("Pattern Matching", "Declarative")
        ),

        // ================= SYSTEMS & HARDWARE =================
        LanguageDefinition(
            id = "zig",
            name = "Zig",
            extension = "zig",
            category = LanguageCategory.SYSTEMS,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2016,
            description = "General-purpose systems language designed to replace C with explicit control and comptime.",
            helloWorldCode = c("""const std = @import("std");

pub fn main() void {
    const stdout = std.io.getStdOut().writer();
    try stdout.print("Hello, Zig! No hidden control flow.\n", .{});
}
"""),
            algorithmCode = c("""fn comptimeFactorial(comptime n: u64) u64 {
    if (n <= 1) return 1;
    return n * comptimeFactorial(n - 1);
}
"""),
            keywords = listOf("comptime", "const", "var", "pub", "fn", "try", "catch", "defer", "errdefer"),
            cheatSheet = "• Comptime execution: run logic during compile time\n• Explicit memory allocation: std.mem.Allocator",
            paradigms = listOf("Systems", "Imperative")
        ),
        LanguageDefinition(
            id = "assembly_x86",
            name = "Assembly (x86_64)",
            extension = "asm",
            category = LanguageCategory.SYSTEMS,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1978,
            description = "Low-level machine architecture mnemonics for Intel/AMD 64-bit processors.",
            helloWorldCode = c("""section .data
    msg db 'Hello, x86_64 Assembly!', 0xA
    len equ § - msg

section .text
    global _start

_start:
    mov rax, 1          ; sys_write
    mov rdi, 1          ; stdout
    mov rsi, msg        ; buffer
    mov rdx, len        ; count
    syscall

    mov rax, 60         ; sys_exit
    xor rdi, rdi        ; code 0
    syscall
"""),
            algorithmCode = c("""; Loop to sum 1 to 10
xor rax, rax   ; sum = 0
mov rcx, 10    ; counter = 10
loop_start:
    add rax, rcx
    dec rcx
    jnz loop_start
"""),
            keywords = listOf("mov", "add", "sub", "xor", "jmp", "cmp", "syscall", "section", "global"),
            cheatSheet = "• Registers: rax (acc), rbx, rcx, rdx, rsi, rdi, rsp, rbp\n• Syscall 1 = write, 60 = exit",
            paradigms = listOf("Low-level", "Assembly")
        ),
        LanguageDefinition(
            id = "assembly_arm",
            name = "Assembly (ARM64)",
            extension = "s",
            category = LanguageCategory.SYSTEMS,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2011,
            description = "AArch64 assembly mnemonics powering modern Android devices and Apple Silicon.",
            helloWorldCode = c(""".global _start
.section .text

_start:
    mov x0, #1          // stdout
    ldr x1, =message    // pointer
    mov x2, #14         // length
    mov x8, #64         // sys_write
    svc #0

    mov x0, #0
    mov x8, #93         // sys_exit
    svc #0

.section .data
message:
    .ascii "Hello, ARM64!\n"
"""),
            algorithmCode = c("""// Add two registers
mov x0, #15
mov x1, #27
add x2, x0, x1
"""),
            keywords = listOf("ldr", "str", "mov", "add", "sub", "svc", "ret", "bl"),
            cheatSheet = "• Registers: x0-x30 (64-bit), w0-w30 (32-bit)\n• svc #0 issues supervisor call",
            paradigms = listOf("Low-level", "RISC")
        ),

        // ================= FUNCTIONAL & LOGIC =================
        LanguageDefinition(
            id = "haskell",
            name = "Haskell",
            extension = "hs",
            category = LanguageCategory.FUNCTIONAL,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1990,
            description = "Purely functional language with strong static typing, type inference, and lazy evaluation.",
            helloWorldCode = c("""-- Haskell 2020
fib :: Int -> Integer
fib 0 = 0
fib 1 = 1
fib n = fib (n - 1) + fib (n - 2)

main :: IO ()
main = do
    putStrLn "Hello, Pure Haskell World!"
    print (map fib [0..8])
"""),
            algorithmCode = c("""-- Elegant Quicksort in Haskell
quicksort :: (Ord a) => [a] -> [a]
quicksort [] = []
quicksort (x:xs) =
    let smaller = quicksort [a | a <- xs, a <= x]
        bigger  = quicksort [a | a <- xs, a > x]
    in smaller ++ [x] ++ bigger

main = print (quicksort [5, 1, 9, 4, 6, 2])
"""),
            keywords = listOf("data", "type", "class", "instance", "where", "let", "in", "do", "case", "of"),
            cheatSheet = "• Monads: IO, Maybe, Either\n• Pattern matching: f [] = 0; f (x:xs) = 1 + f xs\n• Functor & Applicative",
            paradigms = listOf("Pure Functional", "Lazy", "Typed")
        ),
        LanguageDefinition(
            id = "elixir",
            name = "Elixir",
            extension = "ex",
            category = LanguageCategory.FUNCTIONAL,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2012,
            description = "Dynamic functional language running on the Erlang BEAM VM for fault-tolerant concurrency.",
            helloWorldCode = c("""defmodule PolyCode do
  def greet(name) do
    "Hello, #{name}! Leveraging OTP actors and the BEAM VM."
  end
end

IO.puts(PolyCode.greet("Elixir Developer"))
1..5 |> Enum.map(&(&1 * &1)) |> IO.inspect()
"""),
            algorithmCode = c("""defmodule Math do
  def factorial(0), do: 1
  def factorial(n) when n > 0, do: n * factorial(n - 1)
end

IO.puts("Factorial 5: #{Math.factorial(5)}")
"""),
            keywords = listOf("defmodule", "def", "do", "end", "case", "cond", "fn", "spawn", "receive"),
            cheatSheet = "• Pipe operator: data |> step1() |> step2()\n• Pattern matching: {:ok, value} = fetch()\n• Processes: spawn/1, send/2",
            paradigms = listOf("Functional", "Concurrent", "Distributed")
        ),

        // ================= DATA & AI =================
        LanguageDefinition(
            id = "r",
            name = "R",
            extension = "r",
            category = LanguageCategory.DATA_AI,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1993,
            description = "Statistical computing and graphics environment favored by data scientists and researchers.",
            helloWorldCode = c("""# R Statistics
dataset <- c(23, 45, 67, 89, 12, 34, 56)
summary_stats <- summary(dataset)
print(summary_stats)
cat("Standard deviation:", sd(dataset), "\n")
"""),
            algorithmCode = c("""# Linear regression modeling
x <- 1:10
y <- 2 * x + rnorm(10, mean=0, sd=0.5)
model <- lm(y ~ x)
print(coef(model))
"""),
            keywords = listOf("function", "c", "data.frame", "list", "matrix", "lm", "summary"),
            cheatSheet = "• Vectors: c(1, 2, 3)\n• Dataframes: df[column]\n• ggplot2 data visualization",
            paradigms = listOf("Statistical", "Array", "Functional")
        ),
        LanguageDefinition(
            id = "julia",
            name = "Julia",
            extension = "jl",
            category = LanguageCategory.DATA_AI,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2012,
            description = "High-level, high-performance dynamic language for numerical and scientific computing.",
            helloWorldCode = c("""# Julia: Fast as C, easy as Python
function mandelbrot(c, maxiter=50)
    z = c
    for i in 1:maxiter
        if abs(z) > 2 return i end
        z = z^2 + c
    end
    return maxiter
end

println("Mandelbrot at 0+0im: ", mandelbrot(0 + 0im))
"""),
            algorithmCode = c("""# Matrix multiplication
A = rand(4, 4)
B = rand(4, 4)
C = A * B
println("Trace: ", sum(C[i,i] for i in 1:4))
"""),
            keywords = listOf("function", "end", "struct", "macro", "using", "module"),
            cheatSheet = "• Multiple dispatch: functions specialized on all argument types\n• JIT compilation via LLVM",
            paradigms = listOf("Scientific", "Dynamic", "Multiple-dispatch")
        ),

        // ================= SCRIPTING & SHELL =================
        LanguageDefinition(
            id = "bash",
            name = "Bash / Shell",
            extension = "sh",
            category = LanguageCategory.SCRIPTING,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1989,
            description = "The Unix command language and shell scripting environment ubiquitous across servers.",
            helloWorldCode = c("""#!/usr/bin/env bash

echo "Starting system diagnostic..."
HOST=§(hostname 2>/dev/null || echo "localhost")
DATE=§(date)

echo "Host: §{HOST}"
echo "Date: §{DATE}"
echo "Offline Dev Environment: READY"
"""),
            algorithmCode = c("""# File search and count simulation
count=0
for item in *.kt *.java *.py; do
    ((count++))
done
echo "Total source files processed: §count"
"""),
            keywords = listOf("echo", "if", "then", "else", "fi", "for", "while", "do", "done", "case", "esac"),
            cheatSheet = c("• Variables: NAME=\"val\", echo §NAME\n• Arguments: §1, §2, §@\n• Conditionals: [ -f file.txt ], [[ §x == \"y\" ]]"),
            paradigms = listOf("Scripting", "Command")
        ),
        LanguageDefinition(
            id = "powershell",
            name = "PowerShell",
            extension = "ps1",
            category = LanguageCategory.SCRIPTING,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2006,
            description = "Task automation and configuration management framework with object-oriented pipeline.",
            helloWorldCode = c("""# PowerShell Core
Write-Host "Welcome to PowerShell Core!" -ForegroundColor Cyan
§processes = Get-Process | Select-Object -First 3
§processes | ForEach-Object { "§(§_.ProcessName): §(§_.Id)" }
"""),
            algorithmCode = c("""function Get-Factorial([int]§n) {
    if (§n -le 1) { return 1 }
    return §n * (Get-Factorial (§n - 1))
}
Write-Output "Factorial: §(Get-Factorial 6)"
"""),
            keywords = listOf("function", "param", "Write-Host", "Select-Object", "Where-Object", "Foreach-Object"),
            cheatSheet = "• Pipes pass full .NET objects, not just text streams",
            paradigms = listOf("Scripting", "Object-Pipeline")
        ),
        LanguageDefinition(
            id = "lua",
            name = "Lua",
            extension = "lua",
            category = LanguageCategory.SCRIPTING,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1993,
            description = "Lightweight, embeddable scripting language widely used in video games (Roblox, World of Warcraft).",
            helloWorldCode = c("""-- Lua Scripting
function greet(player)
    return "Welcome, " .. player .. "! Enjoy offline scripting."
end

print(greet("Adventurer"))

inventory = { "Sword", "Shield", "Potion" }
for idx, item in ipairs(inventory) do
    print(idx .. ": " .. item)
end
"""),
            algorithmCode = c("""-- Metatables and Object orientation in Lua
Account = { balance = 0 }
function Account:deposit(amount)
    self.balance = self.balance + amount
    print("New balance: $" .. self.balance)
end

Account:deposit(150)
"""),
            keywords = listOf("function", "end", "local", "if", "then", "else", "elseif", "for", "while", "repeat", "until"),
            cheatSheet = "• Tables are the sole data structure (arrays and dictionaries)\n• 1-indexed by default\n• String concat: ..",
            paradigms = listOf("Scripting", "Prototype-based")
        ),
        LanguageDefinition(
            id = "perl",
            name = "Perl",
            extension = "pl",
            category = LanguageCategory.SCRIPTING,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1987,
            description = "Practical Extraction and Reporting Language with unmatched regular expression processing.",
            helloWorldCode = c("""#!/usr/bin/perl
use strict;
use warnings;

my §name = "Perl Hacker";
print "Hello, §name! There is more than one way to do it.\n";

my @langs = ('Perl', 'Python', 'Ruby');
foreach my §l (@langs) {
    print "Language: §l\n";
}
"""),
            algorithmCode = c("""my §text = "The quick brown fox jumps over 42 lazy dogs.";
if (§text =~ /(\\d+)/) {
    print "Found digits: §1\n";
}
"""),
            keywords = listOf("my", "our", "sub", "use", "strict", "warnings", "foreach", "print"),
            cheatSheet = c("• Scalars: §var, Arrays: @arr, Hashes: %hash\n• Regex integrated directly in language syntax"),
            paradigms = listOf("Multi-paradigm", "Text Processing")
        ),
        LanguageDefinition(
            id = "tcl",
            name = "Tcl (Tool Command Language)",
            extension = "tcl",
            category = LanguageCategory.SCRIPTING,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 1988,
            description = "Very simple, string-based scripting language frequently paired with the Tk GUI toolkit.",
            helloWorldCode = c("""# Tcl Script
proc greet {name} {
    puts "Hello, §name! Everything in Tcl is a string."
}
greet "Developer"
"""),
            algorithmCode = c("""set sum 0
for {set i 1} {§i <= 10} {incr i} {
    set sum [expr {§sum + §i}]
}
puts "Sum 1..10: §sum"
"""),
            keywords = listOf("proc", "set", "puts", "expr", "incr", "for", "if"),
            cheatSheet = "• Commands are lists of words: command arg1 arg2",
            paradigms = listOf("Scripting", "String-oriented")
        ),

        // ================= CONFIG & MARKUP =================
        LanguageDefinition(
            id = "markdown",
            name = "Markdown",
            extension = "md",
            category = LanguageCategory.CONFIG_MARKUP,
            executionMode = ExecutionMode.WEB_VIEW_RENDER,
            year = 2004,
            description = "Lightweight markup language with plain-text-formatting syntax.",
            helloWorldCode = c("""# 🚀 PolyCode Offline Documentation

Welcome to **PolyCode IDE**!

### Features
* Supports **100+ programming languages**
* **100% Offline** compilation & simulation
* On-device **AI coding assistant**
* Live **Web & SQLite** preview engines

```kotlin
fun main() {
    println("Code without limits.")
}
```

> "Simplicity is prerequisite for reliability." — Edsger W. Dijkstra
"""),
            algorithmCode = c("""## Big-O Complexity Comparison

| Algorithm | Best | Average | Worst |
|-----------|------|---------|-------|
| QuickSort | O(n log n) | O(n log n) | O(n²) |
| MergeSort | O(n log n) | O(n log n) | O(n log n) |
| Binary Search | O(1) | O(log n) | O(log n) |
"""),
            keywords = listOf("#", "##", "*", "**", "`", "```", ">", "-", "|"),
            cheatSheet = "• Headers: #, ##, ###\n• Bold: **text**, Italic: *text*\n• Links: [title](url)\n• Code: `inline` or ```block```",
            paradigms = listOf("Markup", "Documentation")
        ),
        LanguageDefinition(
            id = "json",
            name = "JSON",
            extension = "json",
            category = LanguageCategory.CONFIG_MARKUP,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2001,
            description = "JavaScript Object Notation open standard file format and data interchange format.",
            helloWorldCode = c("""{
  "project": "PolyCode IDE",
  "version": "1.0.0",
  "offline_capabilities": {
    "compilation": true,
    "ai_assistant": true,
    "languages_supported": 108
  },
  "tags": ["developer-tools", "mobile-ide", "offline-compiler"]
}
"""),
            algorithmCode = c("""{
  "matrix": [
    [1, 0, 0],
    [0, 1, 0],
    [0, 0, 1]
  ],
  "determinant": 1
}
"""),
            keywords = listOf("{", "}", "[", "]", ":", ","),
            cheatSheet = "• Standard data types: String, Number, Object, Array, Boolean, Null",
            paradigms = listOf("Data Serialization")
        ),
        LanguageDefinition(
            id = "solidity",
            name = "Solidity",
            extension = "sol",
            category = LanguageCategory.POPULAR,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2014,
            description = "Object-oriented, high-level language for implementing Ethereum smart contracts.",
            helloWorldCode = c("""// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract PolyToken {
    string public name = "PolyCode Token";
    string public symbol = "POLY";
    uint256 public totalSupply = 1000000;
    mapping(address => uint256) public balances;

    constructor() {
        balances[msg.sender] = totalSupply;
    }
}
"""),
            algorithmCode = c("""function transfer(address to, uint256 amount) public returns (bool) {
    require(balances[msg.sender] >= amount, "Insufficient balance");
    balances[msg.sender] -= amount;
    balances[to] += amount;
    return true;
}
"""),
            keywords = listOf("pragma", "contract", "address", "mapping", "public", "view", "pure", "payable"),
            cheatSheet = "• Gas optimization\n• EVM bytecode target\n• Reentrancy guard",
            paradigms = listOf("Contract-Oriented", "EVM")
        ),
        LanguageDefinition(
            id = "dart",
            name = "Dart",
            extension = "dart",
            category = LanguageCategory.MOBILE,
            executionMode = ExecutionMode.AST_SIMULATOR,
            year = 2011,
            description = "Client-optimized language for fast apps on any platform, powering Flutter.",
            helloWorldCode = c("""void main() {
  final greeting = 'Hello from Dart & Flutter!';
  print(greeting);
  
  final numbers = [1, 2, 3, 4, 5];
  final doubled = numbers.map((n) => n * 2).toList();
  print('Doubled list: §doubled');
}
"""),
            algorithmCode = c("""class ListNode<T> {
  T value;
  ListNode<T>? next;
  ListNode(this.value, [this.next]);
}

void main() {
  final head = ListNode(10, ListNode(20, ListNode(30)));
  print('Head: §{head.value}, Next: §{head.next?.value}');
}
"""),
            keywords = listOf("void", "var", "final", "const", "class", "async", "await", "import"),
            cheatSheet = "• Sound null safety: int? nullableInt\n• Cascade operator: obj..doThis()..doThat()",
            paradigms = listOf("Object-Oriented", "Client-optimized")
        )
    )

    // Generate extended catalog up to 108 languages
    val allLanguages: List<LanguageDefinition> by lazy {
        val initial = languages.toMutableList()
        val extraLanguages = listOf(
            Triple("scala", "Scala", LanguageCategory.POPULAR),
            Triple("groovy", "Groovy", LanguageCategory.POPULAR),
            Triple("fortran", "Fortran", LanguageCategory.SYSTEMS),
            Triple("cobol", "COBOL", LanguageCategory.SYSTEMS),
            Triple("pascal", "Pascal", LanguageCategory.POPULAR),
            Triple("ada", "Ada", LanguageCategory.SYSTEMS),
            Triple("befunge", "Befunge-93", LanguageCategory.ESOTERIC),
            Triple("lolcode", "LOLCODE", LanguageCategory.ESOTERIC),
            Triple("whitespace", "Whitespace", LanguageCategory.ESOTERIC),
            Triple("cow", "COW", LanguageCategory.ESOTERIC),
            Triple("malbolge", "Malbolge", LanguageCategory.ESOTERIC),
            Triple("ook", "Ook!", LanguageCategory.ESOTERIC),
            Triple("arnoldc", "ArnoldC", LanguageCategory.ESOTERIC),
            Triple("scheme", "Scheme", LanguageCategory.FUNCTIONAL),
            Triple("racket", "Racket", LanguageCategory.FUNCTIONAL),
            Triple("elm", "Elm", LanguageCategory.WEB),
            Triple("purescript", "PureScript", LanguageCategory.FUNCTIONAL),
            Triple("idris", "Idris", LanguageCategory.FUNCTIONAL),
            Triple("agda", "Agda", LanguageCategory.FUNCTIONAL),
            Triple("coq", "Coq", LanguageCategory.FUNCTIONAL),
            Triple("forth", "Forth", LanguageCategory.SYSTEMS),
            Triple("postscript", "PostScript", LanguageCategory.CONFIG_MARKUP),
            Triple("smalltalk", "Smalltalk", LanguageCategory.POPULAR),
            Triple("awk", "AWK", LanguageCategory.SCRIPTING),
            Triple("sed", "sed", LanguageCategory.SCRIPTING),
            Triple("coffeescript", "CoffeeScript", LanguageCategory.WEB),
            Triple("actionscript", "ActionScript", LanguageCategory.WEB),
            Triple("crystal", "Crystal", LanguageCategory.POPULAR),
            Triple("ballerina", "Ballerina", LanguageCategory.POPULAR),
            Triple("vyper", "Vyper", LanguageCategory.POPULAR),
            Triple("move", "Move", LanguageCategory.POPULAR),
            Triple("glsl", "GLSL (OpenGL)", LanguageCategory.SYSTEMS),
            Triple("wgsl", "WGSL (WebGPU)", LanguageCategory.WEB),
            Triple("wat", "WebAssembly (WAT)", LanguageCategory.WEB),
            Triple("threejs", "Three.js WebGL", LanguageCategory.WEB),
            Triple("react_jsx", "React JSX", LanguageCategory.WEB),
            Triple("vue_sfc", "Vue SFC", LanguageCategory.WEB),
            Triple("svelte", "Svelte", LanguageCategory.WEB),
            Triple("tailwind", "Tailwind CSS", LanguageCategory.WEB),
            Triple("bootstrap", "Bootstrap 5", LanguageCategory.WEB),
            Triple("sass", "Sass / SCSS", LanguageCategory.WEB),
            Triple("less", "LESS", LanguageCategory.WEB),
            Triple("pug", "Pug / Jade", LanguageCategory.WEB),
            Triple("latex", "LaTeX", LanguageCategory.CONFIG_MARKUP),
            Triple("toml", "TOML", LanguageCategory.CONFIG_MARKUP),
            Triple("graphql", "GraphQL", LanguageCategory.WEB),
            Triple("protobuf", "Protocol Buffers", LanguageCategory.CONFIG_MARKUP),
            Triple("gnuplot", "Gnuplot", LanguageCategory.DATA_AI),
            Triple("graphviz", "Graphviz DOT", LanguageCategory.DATA_AI),
            Triple("mathml", "MathML", LanguageCategory.WEB),
            Triple("apex", "Apex", LanguageCategory.POPULAR),
            Triple("vbnet", "Visual Basic .NET", LanguageCategory.POPULAR),
            Triple("reasonml", "ReasonML", LanguageCategory.FUNCTIONAL),
            Triple("haxe", "Haxe", LanguageCategory.POPULAR),
            Triple("qsharp", "Q# (Quantum)", LanguageCategory.SYSTEMS),
            Triple("cython", "Cython", LanguageCategory.DATA_AI),
            Triple("roff", "Roff / Troff", LanguageCategory.CONFIG_MARKUP),
            Triple("makefile", "Makefile", LanguageCategory.CONFIG_MARKUP),
            Triple("dockerfile", "Dockerfile", LanguageCategory.CONFIG_MARKUP),
            Triple("nginx", "Nginx Config", LanguageCategory.CONFIG_MARKUP),
            Triple("mips", "MIPS Assembly", LanguageCategory.SYSTEMS),
            Triple("riscv", "RISC-V Assembly", LanguageCategory.SYSTEMS),
            Triple("wasm", "WebAssembly Binary", LanguageCategory.WEB),
            Triple("solr", "Apache Lucene/Solr", LanguageCategory.DATA_AI),
            Triple("sparql", "SPARQL", LanguageCategory.DATA_AI),
            Triple("abap", "ABAP", LanguageCategory.POPULAR),
            Triple("rexx", "REXX", LanguageCategory.SCRIPTING),
            Triple("pike", "Pike", LanguageCategory.SCRIPTING),
            Triple("io", "Io Language", LanguageCategory.FUNCTIONAL),
            Triple("standardml", "Standard ML", LanguageCategory.FUNCTIONAL),
            Triple("apl", "APL", LanguageCategory.DATA_AI),
            Triple("j", "J Language", LanguageCategory.DATA_AI),
            Triple("k", "K / q", LanguageCategory.DATA_AI),
            Triple("chapel", "Chapel", LanguageCategory.SYSTEMS),
            Triple("modula2", "Modula-2", LanguageCategory.SYSTEMS),
            Triple("oberon", "Oberon", LanguageCategory.SYSTEMS),
            Triple("simula", "Simula 67", LanguageCategory.POPULAR),
            Triple("plsql", "PL/SQL", LanguageCategory.DATA_AI),
            Triple("transactsql", "T-SQL", LanguageCategory.DATA_AI),
            Triple("cypher", "Neo4j Cypher", LanguageCategory.DATA_AI),
            Triple("jsonnet", "Jsonnet", LanguageCategory.CONFIG_MARKUP),
            Triple("dhall", "Dhall", LanguageCategory.CONFIG_MARKUP),
            Triple("cue", "CUE Lang", LanguageCategory.CONFIG_MARKUP),
            Triple("kdl", "KDL Schema", LanguageCategory.CONFIG_MARKUP),
            Triple("vlang", "V Language", LanguageCategory.SYSTEMS),
            Triple("nimrod", "Nimrod", LanguageCategory.SYSTEMS),
            Triple("dlang", "D Lang", LanguageCategory.SYSTEMS)
        )

        for ((id, name, cat) in extraLanguages) {
            val ext = when {
                id.contains("sql") -> "sql"
                id == "scala" -> "scala"
                id == "latex" -> "tex"
                id == "tailwind" || id == "threejs" || id == "react_jsx" -> "html"
                else -> id.take(4)
            }
            val mode = when {
                cat == LanguageCategory.WEB && (id.contains("tailwind") || id.contains("react") || id.contains("vue") || id.contains("threejs") || id.contains("bootstrap")) -> ExecutionMode.WEB_VIEW_RENDER
                id == "lolcode" -> ExecutionMode.AST_SIMULATOR
                id.contains("sql") -> ExecutionMode.SQL_NATIVE
                id.contains("scheme") || id.contains("racket") -> ExecutionMode.LISP_NATIVE
                else -> ExecutionMode.AST_SIMULATOR
            }

            initial.add(
                LanguageDefinition(
                    id = id,
                    name = name,
                    extension = ext,
                    category = cat,
                    executionMode = mode,
                    year = 2000,
                    description = "Specialized $name implementation supporting offline syntax inspection, AST metrics, and execution simulation.",
                    helloWorldCode = when (id) {
                        "scala" -> "object Main extends App {\n  println(\"Hello from Scala! Functional meets Object-Oriented.\")\n}"
                        "befunge" -> ">25*\"!dlroW olleH\":v\n                v:,_@\n                >  ^"
                        "lolcode" -> "HAI 1.2\n  CAN HAS STDIO?\n  VISIBLE \"HAI WORLD! LOLCODE IS OPERATIONAL\"\nKTHXBYE"
                        "tailwind" -> "<!DOCTYPE html>\n<html><head><script src=\"https://cdn.tailwindcss.com\"></script></head>\n<body class=\"bg-slate-900 text-white flex items-center justify-center min-h-screen\">\n  <div class=\"p-6 bg-slate-800 rounded-xl shadow-lg border border-cyan-500/30 text-center\">\n    <h1 class=\"text-2xl font-bold text-cyan-400\">Tailwind CSS Active</h1>\n    <p class=\"text-slate-400 mt-2\">Utility-first CSS framework sandbox</p>\n  </div>\n</body></html>"
                        "threejs" -> "<!DOCTYPE html>\n<html><body style=\"margin:0; background:#050510;\">\n<canvas id=\"gl\" width=\"360\" height=\"260\"></canvas>\n<script>\nconst c = document.getElementById('gl');\nconst gl = c.getContext('webgl');\ngl.clearColor(0.05, 0.05, 0.15, 1.0);\ngl.clear(gl.COLOR_BUFFER_BIT);\n</script>\n<div style=\"color:#00ffff; font-family:monospace; padding:10px;\">Three.js / WebGL Context Initialized</div>\n</body></html>"
                        "react_jsx" -> "<!DOCTYPE html>\n<html><body style=\"background:#121826; color:#e2e8f0; font-family:sans-serif; padding:20px;\">\n<div id=\"root\"></div>\n<script>\n  document.getElementById('root').innerHTML = `\n    <div style=\"border:1px solid #38bdf8; border-radius:12px; padding:16px; background:#1e293b;\">\n      <h2 style=\"color:#38bdf8; margin:0 0 8px 0;\">⚛️ React Sandbox Component</h2>\n      <p>Simulating offline virtual DOM rendering</p>\n      <button style=\"background:#38bdf8; color:#0f172a; padding:8px 16px; border:none; border-radius:6px; font-weight:bold;\" onclick=\"alert('State updated!')\">Increment Counter</button>\n    </div>\n  `;\n</script></body></html>"
                        else -> "// $name\n// PolyCode Offline Compiler & AST Engine\nprint(\"Hello from $name!\");"
                    },
                    algorithmCode = "// $name Algorithm Demo\n// Evaluates offline time/space complexity",
                    keywords = listOf("func", "def", "class", "var", "val", "return", "if", "else"),
                    cheatSheet = "• Language paradigm: ${cat.displayName}\n• Static offline analysis and compiler diagnostics enabled.",
                    paradigms = listOf(cat.displayName)
                )
            )
        }
        initial
    }

    fun getLanguage(id: String): LanguageDefinition {
        return allLanguages.find { it.id.equals(id, ignoreCase = true) } ?: allLanguages.first()
    }
}
