import java.util.*;

class Solution {
    static class Project {
        String name;
        int startTime;
        int playTime;

        public Project(String name, int startTime, int playTime) {
            this.name = name;
            this.startTime = startTime;
            this.playTime = playTime;
        }
    }

    public String[] solution(String[][] plans) {
        List<String> result = new ArrayList<>();
        Stack<Project> paused = new Stack<>();

        // 시작 시간 기준 정렬
        Arrays.sort(plans, Comparator.comparing(p -> p[1]));

        Queue<Project> queue = new LinkedList<>();
        for (String[] plan : plans) {
            int time = toMinutes(plan[1]);
            int duration = Integer.parseInt(plan[2]);
            queue.offer(new Project(plan[0], time, duration));
        }

        int now = queue.peek().startTime;

        while (!queue.isEmpty()) {
            Project current = queue.poll();
            int currentStart = current.startTime;
            int currentEnd = currentStart + current.playTime;
            int nextStart = queue.isEmpty() ? Integer.MAX_VALUE : queue.peek().startTime;

            // 현재 작업이 다음 작업 시작 전에 끝날 수 있는 경우
            if (currentEnd <= nextStart) {
                now = currentEnd;
                result.add(current.name);

                // 멈춘 과제 이어서 진행
                while (!paused.isEmpty()) {
                    Project pausedProject = paused.pop();
                    int endTime = now + pausedProject.playTime;

                    if (endTime <= nextStart) {
                        now = endTime;
                        result.add(pausedProject.name);
                    } else {
                        // 또 끊겨야 할 경우
                        int timeUsed = nextStart - now;
                        pausedProject.playTime -= timeUsed;
                        now = nextStart;
                        paused.push(pausedProject);
                        break;
                    }
                }

            } else {
                // 과제 끊고 대기열에 추가
                int playBeforeNext = nextStart - currentStart;
                current.playTime -= playBeforeNext;
                now = nextStart;
                paused.push(current);
            }
        }

        // 남은 과제 모두 처리
        while (!paused.isEmpty()) {
            result.add(paused.pop().name);
        }

        return result.toArray(new String[0]);
    }

    private int toMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
