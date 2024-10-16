// https://www.acmicpc.net/problem/1927

/*
* 현성준 : BOJ 1927 최소 힙
* */
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n").map(Number);
solution(input);

function solution(input) {
    let N = input.shift();  // 첫 번째 값을 제거하고 나머지 처리

    class MinHeap {
        constructor() {
            this.heap = [null];  // 최소 힙을 초기화
        }

        insert(element) {
            let current = this.heap.length;
            while (current > 1) {
                const parent = Math.floor(current / 2);
                if (this.heap[parent] > element) {
                    this.heap[current] = this.heap[parent];
                    current = parent;
                } else break;
            }
            this.heap[current] = element;
        }

        remove() {
            let top = this.heap[1];
            if (this.heap.length > 2) {
                this.heap[1] = this.heap.pop();
                let current = 1;
                let leftChild = current * 2;
                let rightChild = current * 2 + 1;
                while (this.heap[leftChild]) {
                    let CompareChild = leftChild;
                    if (this.heap[rightChild] && this.heap[leftChild] > this.heap[rightChild]) {
                        CompareChild = rightChild;
                    }
                    if (this.heap[CompareChild] < this.heap[current]) {
                        const temp = this.heap[current];
                        this.heap[current] = this.heap[CompareChild];
                        this.heap[CompareChild] = temp;
                        current = CompareChild;
                    } else break;
                    leftChild = current * 2;
                    rightChild = current * 2 + 1;
                }
            } else if (this.heap.length === 2) {
                this.heap.pop();
            } else {
                return 0;
            }
            return top;
        }

        getSize() {
            return this.heap.length - 1;
        }
    }

    let answer = '';  // 결과를 저장할 문자열
    const PQ = new MinHeap();

    // 명령에 따라 처리
    for (const Order of input) {
        if (Order === 0) {
            answer += `${PQ.remove()}\n`;  // 0일 때 최소값 제거
        } else {
            PQ.insert(Order);  // 0이 아니면 값 추가
        }
    }

    // 결과 출력
    console.log(answer);
}
