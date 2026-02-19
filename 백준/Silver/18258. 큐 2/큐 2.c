#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_ARR_SIZE 2000001

typedef struct Queue {
    int arr[MAX_ARR_SIZE];
    int rear;
    int front;
    int size;
} Queue;

//push
void push(int x, Queue *q) {
    if (q->size == 0) {      // 비어있으면 인덱스 리셋
        q->front = 0;
        q->rear = -1;
    }
    q->arr[++q->rear] = x;
    q->size++;
}

//pop
int pop(Queue *q) {
    if (q->size == 0) return -1;

    int temp = q->arr[q->front++];
    q->size--;

    if (q->size == 0) {      // 다 비었으면 다시 리셋 (이거 안 하면 다음 push 때 꼬임)
        q->front = -1;
        q->rear = -1;
    }
    return temp;
}

//size
int size(Queue *q) {
    return q->size;
}

//empty
int empty(Queue *q) {
    return (q->size == 0) ? 1 : 0;
}

//front
int front(Queue *q) {
    if (q->size == 0) return -1;
    return q->arr[q->front];
}

//back
int back(Queue *q) {
    if (q->size == 0) return -1;
    return q->arr[q->rear];
}

int main(void) {
    int N;
    scanf("%d", &N);

    // scanf가 남긴 '\n' 제거 (안 하면 첫 fgets가 빈 줄 먹음)
    int ch;
    while ((ch = getchar()) != '\n' && ch != EOF) {}

    Queue *q = (Queue *)malloc(sizeof(Queue));
    q->rear = -1;
    q->front = -1;
    q->size = 0;

    while (N-- != 0) {
        char line[256];
        if (!fgets(line, sizeof(line), stdin)) break;

        char *tok = strtok(line, " \t\r\n");
        if (!tok) { N++; continue; }       // 빈 줄 방어

        char *cmd = tok;

        if (strcmp(cmd, "push") == 0) {
            tok = strtok(NULL, " \t\r\n"); // ★ NULL 이어받기
            int x = atoi(tok);
            push(x, q);
        }
        else if (strcmp(cmd, "pop") == 0) {
            printf("%d\n", pop(q));
        }
        else if (strcmp(cmd, "size") == 0) {
            printf("%d\n", size(q));
        }
        else if (strcmp(cmd, "empty") == 0) {
            printf("%d\n", empty(q));
        }
        else if (strcmp(cmd, "front") == 0) {
            printf("%d\n", front(q));
        }
        else if (strcmp(cmd, "back") == 0) {
            printf("%d\n", back(q));
        }
    }

    free(q);
    return 0;
}
