package hexlet.code;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiffTree {
    private List<DiffNode> nodes = new ArrayList<>();

    public void addNode(DiffNode node) {
        nodes.add(node);
    }
}
