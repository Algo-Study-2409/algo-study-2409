// https://lhoiktiv.tistory.com/1110
// 나중에 보충 하겠습니다
class PriorityQueue {
    constructor(priority) {
        this.heap = [];
        this.pairIsInCorrectOrder = priority;
    }

    getLeftChildIndex(parentIndex) {
        return 2 * parentIndex + 1;
    }

    getRightChildIndex(parentIndex) {
        return 2 * parentIndex + 2;
    }

    getParentIndex(childIndex) {
        return Math.floor((childIndex - 1) / 2);
    }

    hasParent(childIndex) {
        return this.getParentIndex(childIndex) >= 0;
    }

    hasLeftChild(parentIndex) {
        return this.getLeftChildIndex(parentIndex) < this.heap.length;
    }

    hasRightChild(parentIndex) {
        return this.getRightChildIndex(parentIndex) < this.heap.length;
    }

    leftChild(parentIndex) {
        return this.heap[this.getLeftChildIndex(parentIndex)];
    }

    rightChild(parentIndex) {
        return this.heap[this.getRightChildIndex(parentIndex)];
    }

    parent(childIndex) {
        return this.heap[this.getParentIndex(childIndex)];
    }

    swap(indexA, indexB) {
        const tmp = this.heap[indexA];
        this.heap[indexA] = this.heap[indexB];
        this.heap[indexB] = tmp;
    }

    peek() {
        return this.heap.length == 0 ? null : this.heap[0];
    }

    isEmpty() {
        return !this.heap.length;
    }

    pop() {
        if (this.heap.length == 0) {
            return null;
        }

        if (this.heap.length == 1) {
            return this.heap.pop();
        }

        const item = this.heap[0];

        this.heap[0] = this.heap.pop();
        this.bubbleDown();
        return item;
    }

    push(item) {
        this.heap.push(item);
        this.bubbleUp();
        return this;
    }

    bubbleUp() {
        let currentIndex = this.heap.length - 1;

        while (
            this.hasParent(currentIndex) &&
            !this.pairIsInCorrectOrder(this.parent(currentIndex), this.heap[currentIndex])
            ) {
            this.swap(currentIndex, this.getParentIndex(currentIndex));
            currentIndex = this.getParentIndex(currentIndex);
        }
    }

    bubbleDown() {
        let currentIndex = 0;
        let nextIndex = null;

        while (this.hasLeftChild(currentIndex)) {
            if (
                this.hasRightChild(currentIndex) &&
                this.pairIsInCorrectOrder(this.rightChild(currentIndex), this.leftChild(currentIndex))
            ) {
                nextIndex = this.getRightChildIndex(currentIndex);
            } else {
                nextIndex = this.getLeftChildIndex(currentIndex);
            }
            if (this.pairIsInCorrectOrder(this.heap[currentIndex], this.heap[nextIndex])) {
                break;
            }
            this.swap(currentIndex, nextIndex);
            currentIndex = nextIndex;
        }
    }
}

// 우선순위 큐의 정렬 기준 (작은 값이 우선)
const priority = (a, b) => a < b;

// 입력 처리
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
const T = parseInt(input[0]);

for (let t = 0; t < T; t++) {
    const pq = new PriorityQueue(priority);
    const K = parseInt(input[2 * t + 1]);
    const fileList = input[2 * t + 2].split(' ').map(Number);

    fileList.forEach((file) => {
        pq.push(file);
    });

    let cost = 0;

    while (pq.heap.length > 1) {
        // 가장 작은 두 파일을 꺼내서 합침
        const newFile = pq.pop() + pq.pop();
        cost += newFile;
        // 합쳐진 파일을 다시 큐에 넣음
        pq.push(newFile);
    }

    console.log(cost); // 각 테스트 케이스마다 최소 비용 출력
}

// 첫째줄 테스트 데이터 T 임
// 각테스트가 2줄로 이루어짐


