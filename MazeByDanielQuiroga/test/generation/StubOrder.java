package generation;

public class StubOrder implements Order{
	
	int perComplete;
	private int skill;
	private boolean done;
	private Builder build;
	private MazeConfiguration mConfig;
	
	public StubOrder() {
		this.done = true;
		this.skill = 0;
		this.build = Builder.DFS;
	}
	
	public StubOrder(int skill, Builder builder, boolean done){
		this.skill = skill;
		this.build = builder;
		this.done = done;
	}
	
	
	@Override
	public int getSkillLevel() {
		return skill;
	}

	@Override
	public Builder getBuilder() {
		return build;
	}

	@Override
	public boolean isPerfect() {
		return done;
	}

	@Override
	public void deliver(MazeConfiguration mazeConfig) {
		this.mConfig = mazeConfig;
	}

	@Override
	public void updateProgress(int percentage) {
		this.perComplete = percentage;
	}
	
	public MazeConfiguration getMazeConfig(){
		return mConfig;
	}
	
	
	
}