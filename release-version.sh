#!/bin/bash
set -e

usage() {
  cat <<EOF
Usage: ./release-version.sh <release-version> [next-snapshot-version]

Example:
  ./release-version.sh 1.0.0 1.0.1

This script:
  1. Updates all module versions to <release-version>
  2. Commits the release version
  3. Creates a tag v<release-version>
  4. Updates versions to <next-snapshot-version>-SNAPSHOT
  5. Commits the snapshot bump

If next-snapshot-version is omitted, the patch version is auto-incremented.
EOF
  exit 1
}

if [ -z "$1" ]; then
  usage
fi

RELEASE_VERSION="$1"
NEXT_SNAPSHOT_VERSION="$2"
CURRENT_BRANCH=$(git branch --show-current)

if [ -z "$CURRENT_BRANCH" ]; then
  echo "❌ Error: unable to determine current git branch."
  exit 1
fi

#if [ -n "$(git status --porcelain)" ]; then
#  echo "⚠️ Error: working tree is not clean. Commit or stash your changes first."
#  git status --short
#  exit 1
#fi

if [ -z "$NEXT_SNAPSHOT_VERSION" ]; then
  IFS='.' read -r MAJOR MINOR PATCH <<< "$RELEASE_VERSION"
  if [ -z "$MAJOR" ] || [ -z "$MINOR" ] || [ -z "$PATCH" ]; then
    echo "❌ Error: invalid release version format. Expected MAJOR.MINOR.PATCH"
    exit 1
  fi
  NEXT_SNAPSHOT_VERSION="$MAJOR.$MINOR.$((PATCH + 1))-SNAPSHOT"
fi

echo "🚀 Preparing release branch: $CURRENT_BRANCH"
echo "🏷️ Release version: $RELEASE_VERSION"
echo "🔜 Next snapshot version: ${NEXT_SNAPSHOT_VERSION}"

echo "🔧 Updating pom versions to $RELEASE_VERSION..."
mvn versions:set -DnewVersion="$RELEASE_VERSION" -DgenerateBackupPoms=false -DprocessAllModules=true

git add pom.xml '**/pom.xml'

git commit -m "chore(release): prepare v$RELEASE_VERSION"

echo "🏷️ Creating git tag v$RELEASE_VERSION..."
git tag -a "v$RELEASE_VERSION" -m "Release v$RELEASE_VERSION"

echo "📦 Bumping to next snapshot version..."
if [ -z "$NEXT_SNAPSHOT_VERSION" ]; then
    mvn versions:set  -DgenerateBackupPoms=false -DprocessAllModules=true
else
    mvn versions:set -DnewVersion="${NEXT_SNAPSHOT_VERSION}" -DgenerateBackupPoms=false -DprocessAllModules=true
fi

git add pom.xml '**/pom.xml'

git commit -m "chore: bump version to ${NEXT_SNAPSHOT_VERSION}"

echo "✅ Done."
echo "📌 Next steps:"
echo "  git push --follow-tags origin $CURRENT_BRANCH"
