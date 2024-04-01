def solution(board, skill):
    answer = 0
    board_height, board_width = len(board), len(board[0])
    acc = [([0]*(board_width+1)) for _ in range(board_height+1)]
    
    for event in skill:
        skill_type = 0
        
        if event[0] == 1:
            skill_type = -1
        elif event[0] == 2:
            skill_type = 1
        
        startY, startX, endY, endX = event[1], event[2], event[3], event[4]
        degree = event[5]

        acc[startY][startX] += degree * skill_type
        acc[startY][endX + 1] -= degree * skill_type
        acc[endY + 1][startX] -= degree * skill_type
        acc[endY + 1][endX + 1] += degree * skill_type
        
    
    for y in range(board_height):
        for x in range(1, board_width):
            acc[y][x] += acc[y][x - 1]

    
    for x in range(board_width):
        for y in range(1, board_height):
            acc[y][x] += acc[y  - 1][x]

    
    for y in range(board_height):
        for x in range(board_width):    
            board[y][x] += acc[y][x]
            if board[y][x] > 0:
                answer += 1
    
    return answer
